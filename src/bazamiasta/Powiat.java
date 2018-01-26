/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazamiasta;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "powiat", catalog = "test", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Powiat.findAll", query = "SELECT p FROM Powiat p")
    , @NamedQuery(name = "Powiat.findById", query = "SELECT p FROM Powiat p WHERE p.id = :id")
    , @NamedQuery(name = "Powiat.findByNazwa", query = "SELECT p FROM Powiat p WHERE p.nazwa = :nazwa")})
public class Powiat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Nazwa", length = 25)
    private String nazwa;
    @JoinColumn(name = "wojewodztwo", referencedColumnName = "ID")
    @ManyToOne
    private Wojewodztwo wojewodztwo;
    @OneToMany(mappedBy = "powiat")
    private Set<Miasto> miastoSet;

    public Powiat() {
    }

    public Powiat(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Wojewodztwo getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(Wojewodztwo wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    @XmlTransient
    public Set<Miasto> getMiastoSet() {
        return miastoSet;
    }

    public void setMiastoSet(Set<Miasto> miastoSet) {
        this.miastoSet = miastoSet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nazwa);
        hash = 41 * hash + Objects.hashCode(this.wojewodztwo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Powiat other = (Powiat) obj;
        if (!Objects.equals(this.nazwa, other.nazwa)) {
            return false;
        }
        if (!Objects.equals(this.wojewodztwo, other.wojewodztwo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Powiat{" + "id=" + id + ", nazwa=" + nazwa + ", wojewodztwo=" + wojewodztwo.getNazwa() + '}';
    }

    
    
}
