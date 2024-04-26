package org.soneech.truereview.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.model.Role;
import org.soneech.truereview.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
