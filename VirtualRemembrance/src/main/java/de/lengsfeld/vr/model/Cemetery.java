package de.lengsfeld.vr.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

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
    public static final String findById = "Cemetery.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CEMETERY_ID")
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
    private String latitude;

    @Column(name = "LONGITUDE")
    private String longitude;

//    @OneToMany
//    @JoinTable(name = "jnd_graves", joinColumns = @JoinColumn(name="cemetery_fk"), inverseJoinColumns = @JoinColumn(name="grave_fk"))
//    private List<Grave> graves;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getFindAll() {
        return findAll;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<Grave> getGraves() {return graves;}

    public void setGraves(List<Grave> graves) {this.graves = graves; }

//    @OneToMany(mappedBy = "cemetery")
//    public List<Grave> getGraves() {return graves;}

}
