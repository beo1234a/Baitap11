package anhtuan.vn.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import anhtuan.vn.entity.Category_24133072;
import anhtuan.vn.entity.Video_24133072;
import anhtuan.vn.service.CategoryService_24133072;
import anhtuan.vn.service.ICategoryService_24133072;
import anhtuan.vn.service.IVideoService_24133072;
import anhtuan.vn.service.VideoService_24133072;

@WebServlet(
        urlPatterns = {
                "/admin/videos",
                "/admin/videos/add",
                "/admin/videos/edit",
                "/admin/videos/delete"
        })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class VideoAdminController_24133072 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final int PAGE_SIZE = 6;

    private final IVideoService_24133072 videoService =
            new VideoService_24133072();

    private final ICategoryService_24133072 categoryService =
            new CategoryService_24133072();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {

        case "/admin/videos/add":
            showAddForm(request, response);
            break;

        case "/admin/videos/edit":
            showEditForm(request, response);
            break;

        case "/admin/videos/delete":
            deleteVideo(request, response);
            break;

        default:
            showVideoList(request, response);
            break;
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String path = request.getServletPath();

        if ("/admin/videos/add".equals(path)) {

            insertVideo(request, response);

        } else if ("/admin/videos/edit".equals(path)) {

            updateVideo(request, response);

        } else {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/videos");
        }
    }

    private void showVideoList(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int page =
                parsePositiveInt(
                        request.getParameter("page"),
                        1);

        int totalPages =
                videoService.totalPages(PAGE_SIZE);

        if (totalPages < 1) {
            totalPages = 1;
        }

        if (page > totalPages) {
            page = totalPages;
        }

        List<Video_24133072> videos =
                videoService.findPage(
                        page,
                        PAGE_SIZE);

        request.setAttribute(
                "videos",
                videos);

        request.setAttribute(
                "currentPage",
                page);

        request.setAttribute(
                "totalPages",
                totalPages);

        request.setAttribute(
                "totalVideos",
                videoService.count());

        request.getRequestDispatcher(
                "/views/admin/video/list.jsp")
                .forward(request, response);
    }

    private void showAddForm(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "categories",
                categoryService.findAll());

        request.getRequestDispatcher(
                "/views/admin/video/add.jsp")
                .forward(request, response);
    }

    private void insertVideo(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String title =
                request.getParameter("title");

        String description =
                request.getParameter("description");

        Integer categoryId =
                parseInteger(
                        request.getParameter(
                                "categoryId"));

        boolean active =
                request.getParameter("active") != null;

        if (title == null
                || title.trim().isEmpty()
                || categoryId == null) {

            request.setAttribute(
                    "error",
                    "Vui lòng nhập tiêu đề và chọn Category.");

            showAddForm(request, response);
            return;
        }

        Category_24133072 category =
                categoryService.findById(
                        categoryId);

        if (category == null) {

            request.setAttribute(
                    "error",
                    "Category không tồn tại.");

            showAddForm(request, response);
            return;
        }

        String poster =
                savePoster(
                        request,
                        "posterFile");

        Video_24133072 video =
                new Video_24133072();

        video.setVideoId(
                videoService.generateVideoId());

        video.setTitle(
                title.trim());

        video.setPoster(
                poster);

        video.setViews(
                0);

        video.setDescription(
                description == null
                        ? null
                        : description.trim());

        video.setActive(
                active);

        video.setCategory(
                category);

        videoService.insert(
                video);

        response.sendRedirect(
                request.getContextPath()
                + "/admin/videos");
    }

    private void showEditForm(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id =
                request.getParameter("id");

        Video_24133072 video =
                videoService.findById(id);

        if (video == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/videos");

            return;
        }

        request.setAttribute(
                "video",
                video);

        request.setAttribute(
                "categories",
                categoryService.findAll());

        request.getRequestDispatcher(
                "/views/admin/video/edit.jsp")
                .forward(request, response);
    }

    private void updateVideo(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String videoId =
                request.getParameter("videoId");

        Video_24133072 video =
                videoService.findById(
                        videoId);

        if (video == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/videos");

            return;
        }

        String title =
                request.getParameter("title");

        String description =
                request.getParameter("description");

        Integer views =
                parseInteger(
                        request.getParameter(
                                "views"));

        Integer categoryId =
                parseInteger(
                        request.getParameter(
                                "categoryId"));

        boolean active =
                request.getParameter("active") != null;

        if (title == null
                || title.trim().isEmpty()
                || categoryId == null) {

            request.setAttribute(
                    "error",
                    "Vui lòng nhập tiêu đề và chọn Category.");

            showEditForm(request, response);
            return;
        }

        Category_24133072 category =
                categoryService.findById(
                        categoryId);

        if (category == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/videos");

            return;
        }

        String newPoster =
                savePoster(
                        request,
                        "posterFile");

        if (newPoster != null
                && !newPoster.isEmpty()) {

            video.setPoster(
                    newPoster);
        }

        video.setTitle(
                title.trim());

        video.setViews(
                views == null
                        ? 0
                        : views);

        video.setDescription(
                description == null
                        ? null
                        : description.trim());

        video.setActive(
                active);

        video.setCategory(
                category);

        videoService.update(
                video);

        response.sendRedirect(
                request.getContextPath()
                + "/admin/videos");
    }

    private void deleteVideo(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String id =
                request.getParameter("id");

        if (id != null
                && !id.trim().isEmpty()) {

            videoService.delete(id);
        }

        response.sendRedirect(
                request.getContextPath()
                + "/admin/videos");
    }

    private String savePoster(
            HttpServletRequest request,
            String partName)
            throws IOException, ServletException {

        Part part =
                request.getPart(partName);

        if (part == null
                || part.getSize() == 0) {

            return null;
        }

        String originalFileName =
                Paths.get(
                        part.getSubmittedFileName())
                        .getFileName()
                        .toString();

        if (originalFileName == null
                || originalFileName.trim().isEmpty()) {

            return null;
        }

        String extension = "";

        int dotIndex =
                originalFileName.lastIndexOf('.');

        if (dotIndex >= 0) {

            extension =
                    originalFileName
                            .substring(dotIndex)
                            .toLowerCase();
        }

        if (!extension.equals(".jpg")
                && !extension.equals(".jpeg")
                && !extension.equals(".png")
                && !extension.equals(".webp")) {

            throw new ServletException(
                    "Chỉ cho phép JPG, JPEG, PNG hoặc WEBP.");
        }

        String fileName =
                "poster_"
                + System.currentTimeMillis()
                + extension;

        String realPath =
                getServletContext()
                        .getRealPath(
                                "/uploads/posters");

        if (realPath == null) {

            throw new ServletException(
                    "Không xác định được thư mục upload.");
        }

        Path uploadDirectory =
                Paths.get(realPath);

        if (Files.exists(uploadDirectory)
                && !Files.isDirectory(uploadDirectory)) {

            Files.delete(uploadDirectory);
        }

        Files.createDirectories(
                uploadDirectory);

        Path destination =
                uploadDirectory.resolve(
                        fileName);

        try (InputStream inputStream =
                part.getInputStream()) {

            Files.copy(
                    inputStream,
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println(
                "UPLOAD POSTER THANH CONG: "
                + destination.toAbsolutePath());

        return fileName;
    }

    private int parsePositiveInt(
            String value,
            int defaultValue) {

        try {

            int number =
                    Integer.parseInt(value);

            return number > 0
                    ? number
                    : defaultValue;

        } catch (Exception e) {

            return defaultValue;
        }
    }

    private Integer parseInteger(
            String value) {

        try {

            return Integer.valueOf(value);

        } catch (Exception e) {

            return null;
        }
    }
}