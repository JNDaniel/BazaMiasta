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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "wojewodztwo", catalog = "test", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wojewodztwo.findAll", query = "SELECT w FROM Wojewodztwo w")
    , @NamedQuery(name = "Wojewodztwo.findById", query = "SELECT w FROM Wojewodztwo w WHERE w.id = :id")
    , @NamedQuery(name = "Wojewodztwo.findByNazwa", query = "SELECT w FROM Wojewodztwo w WHERE w.nazwa = :nazwa")})
public class Wojewodztwo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Nazwa", length = 19)
    private String nazwa;
    @OneToMany(mappedBy = "wojewodztwo")
    private Set<Powiat> powiatSet;
    @OneToMany(mappedBy = "wojewodztwo")
    private Set<Miasto> miastoSet;

    public Wojewodztwo() {
        
    }
    public Wojewodztwo(Integer id) {
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

    @XmlTransient
    public Set<Powiat> getPowiatSet() {
        return powiatSet;
    }

    public void setPowiatSet(Set<Powiat> powiatSet) {
        this.powiatSet = powiatSet;
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
        hash = 47 * hash + Objects.hashCode(this.nazwa);
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
        final Wojewodztwo other = (Wojewodztwo) obj;
        if (!Objects.equals(this.nazwa, other.nazwa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Wojewodztwo{" + "id=" + id + ", nazwa=" + nazwa + '}';
    }


    
}
