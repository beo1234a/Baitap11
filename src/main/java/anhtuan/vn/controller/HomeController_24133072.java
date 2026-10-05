package anhtuan.vn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anhtuan.vn.entity.Category_24133072;
import anhtuan.vn.entity.Video_24133072;
import anhtuan.vn.service.CategoryService_24133072;
import anhtuan.vn.service.FavoriteService_24133072;
import anhtuan.vn.service.ICategoryService_24133072;
import anhtuan.vn.service.IFavoriteService_24133072;
import anhtuan.vn.service.IShareService_24133072;
import anhtuan.vn.service.IVideoService_24133072;
import anhtuan.vn.service.ShareService_24133072;
import anhtuan.vn.service.VideoService_24133072;

@WebServlet(urlPatterns = { "/home" })
public class HomeController_24133072
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final int PAGE_SIZE = 3;

    private final ICategoryService_24133072 categoryService =
            new CategoryService_24133072();

    private final IVideoService_24133072 videoService =
            new VideoService_24133072();

    private final IFavoriteService_24133072 favoriteService =
            new FavoriteService_24133072();

    private final IShareService_24133072 shareService =
            new ShareService_24133072();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Category_24133072> categories =
                categoryService.findAll();

        request.setAttribute(
                "categories",
                categories);

        if (categories == null
                || categories.isEmpty()) {

            request.setAttribute(
                    "message",
                    "Chưa có Category.");

            request.getRequestDispatcher(
                    "/views/user/home.jsp")
                    .forward(
                            request,
                            response);

            return;
        }

        Integer categoryId =
                parseInteger(
                        request.getParameter(
                                "categoryId"));

        Category_24133072 selectedCategory =
                null;

        if (categoryId != null) {

            selectedCategory =
                    categoryService.findById(
                            categoryId);
        }

        if (selectedCategory == null) {

            for (Category_24133072 category
                    : categories) {

                if (Boolean.TRUE.equals(
                        category.getStatus())) {

                    selectedCategory =
                            category;

                    break;
                }
            }
        }

        if (selectedCategory == null) {

            selectedCategory =
                    categories.get(0);
        }

        int page =
                parsePositiveInt(
                        request.getParameter("page"),
                        1);

        long totalVideos =
                videoService.countByCategory(
                        selectedCategory
                                .getCategoryId());

        int totalPages =
                videoService.totalPagesByCategory(
                        selectedCategory
                                .getCategoryId(),
                        PAGE_SIZE);

        if (totalPages < 1) {
            totalPages = 1;
        }

        if (page > totalPages) {
            page = totalPages;
        }

        List<Video_24133072> videos =
                videoService.findByCategoryPage(
                        selectedCategory
                                .getCategoryId(),
                        page,
                        PAGE_SIZE);

        Map<String, Long> likeCounts =
                new HashMap<>();

        Map<String, Long> shareCounts =
                new HashMap<>();

        for (Video_24133072 video : videos) {

            String videoId =
                    video.getVideoId();

            likeCounts.put(
                    videoId,
                    favoriteService.countByVideo(
                            videoId));

            shareCounts.put(
                    videoId,
                    shareService.countByVideo(
                            videoId));
        }

        Map<Integer, Long> categoryCounts =
                new HashMap<>();

        for (Category_24133072 category
                : categories) {

            categoryCounts.put(
                    category.getCategoryId(),
                    videoService.countByCategory(
                            category.getCategoryId()));
        }

        request.setAttribute(
                "selectedCategory",
                selectedCategory);

        request.setAttribute(
                "videos",
                videos);

        request.setAttribute(
                "likeCounts",
                likeCounts);

        request.setAttribute(
                "shareCounts",
                shareCounts);

        request.setAttribute(
                "categoryCounts",
                categoryCounts);

        request.setAttribute(
                "totalVideos",
                totalVideos);

        request.setAttribute(
                "currentPage",
                page);

        request.setAttribute(
                "totalPages",
                totalPages);

        request.getRequestDispatcher(
                "/views/user/home.jsp")
                .forward(
                        request,
                        response);
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