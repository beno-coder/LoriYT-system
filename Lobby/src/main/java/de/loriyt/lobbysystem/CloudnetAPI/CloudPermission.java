package de.loriyt.lobbysystem.CloudnetAPI;

import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.dytanic.cloudnet.driver.permission.PermissionUserGroupInfo;
import de.dytanic.cloudnet.ext.cloudperms.CloudPermissionsManagement;

import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CloudPermission {
    public CloudPermission() {
    }

    public String getRankName(UUID uuid) {
        return CloudPermissionsManagement.getInstance().getHighestPermissionGroup(this.getIPermissionUser(uuid)).getName();
    }

    public String getRankColor(UUID uuid) {
        return CloudPermissionsManagement.getInstance().getHighestPermissionGroup(this.getIPermissionUser(uuid)).getColor();
    }

    public IPermissionUser getIPermissionUser(UUID uuid) {
        return CloudPermissionsManagement.getInstance().getUser(uuid);
    }

    public void addGroup(UUID uuid, String group) {
        IPermissionUser permissionUser = this.getIPermissionUser(uuid);
        permissionUser.addGroup(group);
        CloudPermissionsManagement.getInstance().updateUser(permissionUser);
    }

    public void addTempGroup(UUID uuid, String group, int l, TimeUnit unit) {
        IPermissionUser permissionUser = this.getIPermissionUser(uuid);
        permissionUser.addGroup(group, (long)l, unit);
        CloudPermissionsManagement.getInstance().updateUser(permissionUser);
    }

    public void removeGroup(UUID uuid, String group) {
        IPermissionUser permissionUser = this.getIPermissionUser(uuid);
        permissionUser.removeGroup(group);
        CloudPermissionsManagement.getInstance().updateUser(permissionUser);
    }

    public void addPermission(UUID uuid, String permission) {
        IPermissionUser permissionUser = this.getIPermissionUser(uuid);
        permissionUser.addPermission(permission);
        CloudPermissionsManagement.getInstance().updateUser(permissionUser);
    }

    public void removePermission(UUID uuid, String permission) {
        IPermissionUser permissionUser = this.getIPermissionUser(uuid);
        permissionUser.removePermission(permission);
        CloudPermissionsManagement.getInstance().updateUser(permissionUser);
    }

    public void removeGroups(UUID uuid) {
        IPermissionUser permissionUser = this.getIPermissionUser(uuid);
        Iterator var3 = permissionUser.getGroups().iterator();

        while(var3.hasNext()) {
            PermissionUserGroupInfo info = (PermissionUserGroupInfo)var3.next();
            this.removeGroup(uuid, info.getGroup());
        }

    }
}

