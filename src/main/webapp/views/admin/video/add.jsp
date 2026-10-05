<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <title>Thêm Video</title>
</head>

<body>

<div class="surface form-card">

    <div class="page-header">

        <div>

            <h1>Thêm Video</h1>

            <div class="page-subtitle">
                Mã Video sẽ được tự động tạo
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
          action="${pageContext.request.contextPath}/admin/videos/add">

        <div class="form-group">

            <label class="form-label">
                Tiêu đề Video
            </label>

            <input class="form-control"
                   type="text"
                   name="title"
                   placeholder="Nhập tiêu đề Video"
                   required>

        </div>

        <div class="form-group">

            <label class="form-label">
                Poster
            </label>

            <input class="form-control"
                   type="file"
                   name="posterFile"
                   accept=".jpg,.jpeg,.png,.webp,image/*">

            <span class="form-help">
                Hỗ trợ JPG, JPEG, PNG, WEBP. Tối đa 5MB.
            </span>

        </div>

        <div class="form-group">

            <label class="form-label">
                Category
            </label>

            <select class="form-control"
                    name="categoryId"
                    required>

                <option value="">
                    -- Chọn Category --
                </option>

                <c:forEach var="category"
                           items="${categories}">

                    <option value="${category.categoryId}">
                        ${category.categoryname}
                    </option>

                </c:forEach>

            </select>

        </div>

        <div class="form-group">

            <label class="form-label">
                Mô tả
            </label>

            <textarea class="form-control"
                      name="description"
                      placeholder="Nhập mô tả Video"></textarea>

        </div>

        <div class="form-group">

            <label>
                <input type="checkbox"
                       name="active"
                       checked>
                Kích hoạt Video
            </label>

        </div>

        <div class="form-actions">

            <button class="btn btn-success"
                    type="submit">
                Lưu Video
            </button>

            <a class="btn btn-secondary"
               href="${pageContext.request.contextPath}/admin/videos">
                Hủy
            </a>

        </div>

    </form>

</div>

</body>

</html>