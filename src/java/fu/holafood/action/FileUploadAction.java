package fu.holafood.action;

import java.io.File;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport implements ServletContextAware {

    private File userImage;
    private String userImageContentType;
    private String userImageFileName;
    private ServletContext servletContext;

    public void setServletContext(ServletContext context) {
        servletContext = context;
    }

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }

    public String execute() {
        try {
            String filePath = servletContext.getRealPath("/").concat("admin\\upload");
            System.out.println("Image location:" + filePath);
            File fileToCreate = new File(filePath, this.userImageFileName);
            FileUtils.copyFile(this.userImage, fileToCreate);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return INPUT;
        }
        return SUCCESS;
    }

}
