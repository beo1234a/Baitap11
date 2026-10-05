package anhtuan.vn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anhtuan.vn.entity.User_24133072;

@WebFilter(urlPatterns = { "/admin/*" })
public class AdminFilter_24133072 implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        HttpSession session =
                httpRequest.getSession(false);

        if (session == null) {

            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                    + "/login");

            return;
        }

        User_24133072 account =
                (User_24133072)
                session.getAttribute("account");

        if (account == null
                || !Boolean.TRUE.equals(
                        account.getAdmin())) {

            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                    + "/login");

            return;
        }

        chain.doFilter(
                request,
                response);
    }
}