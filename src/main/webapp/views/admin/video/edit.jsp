<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <title>Cập nhật Video</title>
</head>

<body>

<div class="surface form-card">

    <div class="page-header">

        <div>

            <h1>Cập nhật Video</h1>

            <div class="page-subtitle">
                Chỉnh sửa thông tin ${video.videoId}
            </div>

        </div>

    </div>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
    </c:if>

    <form method="post"
          enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/admin/videos/edit">

        <input type="hidden"
               name="videoId"
               value="${video.videoId}">

        <div class="form-group">

            <label class="form-label">
                Mã Video
            </label>

            <input class="form-control"
                   type="text"
                   value="${video.videoId}"
                   readonly>

        </div>

        <div class="form-group">

            <label class="form-label">
                Tiêu đề
            </label>

            <input class="form-control"
                   type="text"
                   name="title"
                   value="${video.title}"
                   required>

        </div>

        <div class="form-group">

            <label class="form-label">
                Poster hiện tại
            </label>

            <c:choose>

                <c:when test="${not empty video.poster}">

                    <img class="preview-poster"
                         src="${pageContext.request.contextPath}/uploads/posters/${video.poster}"
                         alt="${video.title}">

                </c:when>

                <c:otherwise>
                    Chưa có poster
                </c:otherwise>

            </c:choose>

        </div>

        <div class="form-group">

            <label class="form-label">
                Chọn poster mới
            </label>

            <input class="form-control"
                   type="file"
                   name="posterFile"
                   accept=".jpg,.jpeg,.png,.webp,image/*">

            <span class="form-help">
                Để trống nếu muốn giữ poster hiện tại.
            </span>

        </div>

        <div class="form-group">

            <label class="form-label">
                Category
            </label>

            <select class="form-control"
                    name="categoryId"
                    required>

                <c:forEach var="category"
                           items="${categories}">

                    <option value="${category.categoryId}"
                            ${category.categoryId == video.category.categoryId ? 'selected' : ''}>

                        ${category.categoryname}

                    </option>

                </c:forEach>

            </select>

        </div>

        <div class="form-group">

            <label class="form-label">
                Views
            </label>

            <input class="form-control"
                   type="number"
                   min="0"
                   name="views"
                   value="${video.views}">

        </div>


        <div class="form-group">
            <label class="form-label">Giá bán (VNĐ)</label>
            <input class="form-control" type="number" name="price" min="0" step="1000" value="${video.price}" required>
        </div>

        <div class="form-group">
            <label class="form-label">Tồn kho</label>
            <input class="form-control" type="number" name="quantity" min="0" value="${video.quantity}" required>
        </div>

        <div class="form-group">

            <label class="form-label">
                Mô tả
            </label>

            <textarea class="form-control"
                      name="description">${video.description}</textarea>

        </div>

        <div class="form-group">

            <label>
                <input type="checkbox"
                       name="active"
                       ${video.active ? 'checked' : ''}>
                Kích hoạt Video
            </label>

        </div>

        <div class="form-actions">

            <button class="btn btn-primary"
                    type="submit">
                Lưu thay đổi
            </button>

            <a class="btn btn-secondary"
               href="${pageContext.request.contextPath}/admin/videos">
                Quay lại
            </a>

        </div>

    </form>

</div>

</body>

</html>