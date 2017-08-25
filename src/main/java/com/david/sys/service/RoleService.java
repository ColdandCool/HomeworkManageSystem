package com.david.sys.service;

import com.david.common.service.CrudService;
import com.david.sys.dao.IRoleDao;
import com.david.sys.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Authority service
 *
 * @author David
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends CrudService<IRoleDao, Role> {

    @Autowired
    ResourceService resourceService;

    public Set<String> findRoles(String... roleIds) {
        Set<String> roles = new HashSet<String>();
        for (String roleId : roleIds) {
            Role role = get(roleId);
            if (role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    public Set<String> findPermissions(String[] roleIds) {
        Set<String> resourceIds = new HashSet<String>();
        List<Role> roles = dao.getRoles(roleIds);
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            if (role != null) {
                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }
}
