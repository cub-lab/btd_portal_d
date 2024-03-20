package ro.cub.btddigitalportals.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ro.cub.btddigitalportals.BTDDigitalPortalsApplication;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TemplateUtils {

    public static String makeActivationEmail(String firstName, String lastName, String activationLink, String languageTag) {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setFallbackOnNullLoopVariable(false);
        configuration.setClassForTemplateLoading(BTDDigitalPortalsApplication.class, "/");

        try {
            Template template =
                    configuration.getTemplate("ro/cub/btddigitalportals/email/account-activation-" + languageTag + ".ftl");
            Map<String, String> params = new HashMap<>();
            params.put("firstName", firstName);
            params.put("lastName", lastName);
            params.put("activationLink", activationLink);
            StringWriter sw = new StringWriter();
            template.process(params, sw);
            return sw.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }

    }
}
