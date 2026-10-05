package anhtuan.vn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anhtuan.vn.entity.Video_24133072;
import anhtuan.vn.service.FavoriteService_24133072;
import anhtuan.vn.service.IFavoriteService_24133072;
import anhtuan.vn.service.IShareService_24133072;
import anhtuan.vn.service.IVideoService_24133072;
import anhtuan.vn.service.ShareService_24133072;
import anhtuan.vn.service.VideoService_24133072;

@WebServlet(urlPatterns = { "/video/detail" })
public class VideoDetailController_24133072
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

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

        String videoId =
                request.getParameter("id");

        if (videoId == null
                || videoId.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/home");

            return;
        }

        Video_24133072 video =
                videoService.findById(
                        videoId.trim());

        if (video == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/home");

            return;
        }

        long likeCount =
                favoriteService.countByVideo(
                        video.getVideoId());

        long shareCount =
                shareService.countByVideo(
                        video.getVideoId());

        request.setAttribute(
                "video",
                video);

        request.setAttribute(
                "likeCount",
                likeCount);

        request.setAttribute(
                "shareCount",
                shareCount);

        request.getRequestDispatcher(
                "/views/user/video-detail.jsp")
                .forward(
                        request,
                        response);
    }
}