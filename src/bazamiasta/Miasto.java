/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazamiasta;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "miasto", catalog = "test", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miasto.findAll", query = "SELECT m FROM Miasto m")
    , @NamedQuery(name = "Miasto.findById", query = "SELECT m FROM Miasto m WHERE m.id = :id")
    , @NamedQuery(name = "Miasto.findByNazwa", query = "SELECT m FROM Miasto m WHERE m.nazwa = :nazwa")})
public class Miasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Nazwa", length = 32)
    private String nazwa;
    @JoinColumn(name = "powiat", referencedColumnName = "ID")
    @ManyToOne
    private Powiat powiat;
    @JoinColumn(name = "wojewodztwo", referencedColumnName = "ID")
    @ManyToOne
    private Wojewodztwo wojewodztwo;

    public Miasto() {
    }

    public Miasto(Integer id) {
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

    public Powiat getPowiat() {
        return powiat;
    }

    public void setPowiat(Powiat powiat) {
        this.powiat = powiat;
    }

    public Wojewodztwo getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(Wojewodztwo wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nazwa);
        hash = 41 * hash + Objects.hashCode(this.powiat);
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
        final Miasto other = (Miasto) obj;
        if (!Objects.equals(this.nazwa, other.nazwa)) {
            return false;
        }
        if (!Objects.equals(this.powiat, other.powiat)) {
            return false;
        }
        if (!Objects.equals(this.wojewodztwo, other.wojewodztwo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Miasto{" + "nazwa=" + nazwa + ", powiat=" + powiat + ", wojewodztwo=" + wojewodztwo + '}';
    }
    
}
