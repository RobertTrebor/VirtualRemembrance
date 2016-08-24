package de.lengsfeld.vr.model;

import java.io.Serializable;
import java.util.List;
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

@Entity
@Table(name = "CEMETERY")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Cemetery.findc", query = "SELECT c FROM Cemetery c WHERE c.id = 1"),
        @NamedQuery(name = "Cemetery.findAll", query = "SELECT c FROM Cemetery c"),
        @NamedQuery(name = "Cemetery.findById", query = "SELECT c FROM Cemetery c WHERE c.id = :id"),
        @NamedQuery(name = "Cemetery.findByName", query = "SELECT c FROM Cemetery c WHERE c.name = :name"),
        @NamedQuery(name = "Cemetery.findByCity", query = "SELECT c FROM Cemetery c WHERE c.city = :city"),
        @NamedQuery(name = "Cemetery.findByCountry", query = "SELECT c FROM Cemetery c WHERE c.country = :country"),
        @NamedQuery(name = "Cemetery.findByZipcode", query = "SELECT c FROM Cemetery c WHERE c.zipcode = :zipcode"),
        @NamedQuery(name = "Cemetery.findByStreet", query = "SELECT c FROM Cemetery c WHERE c.street = :street"),
        @NamedQuery(name = "Cemetery.findByLatitude", query = "SELECT c FROM Cemetery c WHERE c.latitude = :latitude"),
        @NamedQuery(name = "Cemetery.findByLongitude", query = "SELECT c FROM Cemetery c WHERE c.longitude = :longitude")})
public class Cemetery implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String findAll = "Cemetery.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "CITY", length = 100)
    private String city;
    @Basic(optional = false)
    @Column(name = "COUNTRY", length = 100)
    private String country;
    @Column(name = "ZIPCODE", length = 20)
    private String zipcode;
    @Column(name = "STREET", length = 100)
    private String street;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;

    @OneToMany(mappedBy = "cemetery")
    private List<Grave> graves;

    public Cemetery() {
    }

    public Cemetery(Long id) {
        this.id = id;
    }

    public Cemetery(String name, String country) {
        this.name = name;
        this.country = country;
    }
}