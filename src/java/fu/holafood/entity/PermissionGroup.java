/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.entity;

/**
 *
 * @author NhocNho
 */
public class PermissionGroup {

    private int id;
    private int permissionId;
    private String name;

    public PermissionGroup() {
    }

    public PermissionGroup(int id, int permissionId, String name) {
        this.id = id;
        this.permissionId = permissionId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
