package anhtuan.vn.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SiteMeshFilter_24133072
        extends ConfigurableSiteMeshFilter {

    private static final long serialVersionUID = 1L;

    @Override
    protected void applyCustomConfiguration(
            SiteMeshFilterBuilder builder) {

        builder
            .addDecoratorPath(
                    "/admin/*",
                    "admin.jsp")

            .addDecoratorPath(
                    "/*",
                    "user.jsp")

            .addExcludedPath(
                    "/assets/*")

            .addExcludedPath(
                    "/uploads/*");
    }
}