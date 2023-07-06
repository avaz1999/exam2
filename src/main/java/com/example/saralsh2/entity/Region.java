package com.example.saralsh2.entity;

import com.example.saralsh2.base.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "regions")
public class Region extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL)
    private List<Organization> organizations;
}
