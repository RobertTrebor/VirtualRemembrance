package de.lengsfeld.vr.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "GRAVE")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Grave.findg", query = "SELECT g FROM Grave g WHERE g.id = 1"),
        @NamedQuery(name = "Grave.findAll", query = "SELECT g FROM Grave g"),
        @NamedQuery(name = "Grave.findByGId", query = "SELECT g FROM Grave g WHERE g.id = :id"),
        @NamedQuery(name = "Grave.findByFirstname", query = "SELECT g FROM Grave g WHERE g.firstname = :firstname"),
        @NamedQuery(name = "Grave.findByLastname", query = "SELECT g FROM Grave g WHERE g.lastname = :lastname"),
        @NamedQuery(name = "Grave.findBySex", query = "SELECT g FROM Grave g WHERE g.sex = :sex"),
        @NamedQuery(name = "Grave.findByDatebirth", query = "SELECT g FROM Grave g WHERE g.datebirth = :datebirth"),
        @NamedQuery(name = "Grave.findByDatedeath", query = "SELECT g FROM Grave g WHERE g.datedeath = :datedeath"),
        @NamedQuery(name = "Grave.findByCemetery", query = "SELECT g FROM Grave g WHERE g.cemetery = :cemetery"),
        @NamedQuery(name = "Grave.findByGraveLoc", query = "SELECT g FROM Grave g WHERE g.graveLoc = :graveLoc"),
        @NamedQuery(name = "Grave.findByLatitude", query = "SELECT g FROM Grave g WHERE g.latitude = :latitude"),
        @NamedQuery(name = "Grave.findByLongitude", query = "SELECT g FROM Grave g WHERE g.longitude = :longitude"),
        @NamedQuery(name = "Grave.findByVitaPath", query = "SELECT g FROM Grave g WHERE g.vitaPath = :vitaPath"),
        @NamedQuery(name = "Grave.findByTombstonePath", query = "SELECT g FROM Grave g WHERE g.tombstonePath = :tombstonePath")})
public class Grave implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String findAll = "Grave.findAll";
    public static final String findByCemetery = "Grave.findByCemetery";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "SEX")
    private String sex;
    @Column(name = "DATEBIRTH")
    @Temporal(TemporalType.DATE)
    private Date datebirth;
    @Column(name = "DATEDEATH")
    @Temporal(TemporalType.DATE)
    private Date datedeath;

    @ManyToOne
    private Cemetery cemetery;
    @Column(name = "GRAVE_LOC")
    private String graveLoc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "VITA_PATH")
    private String vitaPath;
    @Column(name = "TOMBSTONE_PATH")
    private String tombstonePath;

    public Grave() {
    }

    public Grave(Long id) {
        this.id = id;
    }

    public Grave(String firstname, String lastname, String sex, Cemetery cemetery) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.sex = sex;
        this.cemetery = cemetery;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getFindAll() {
        return findAll;
    }

    public static String getFindByCemetery() {
        return findByCemetery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(Date datebirth) {
        this.datebirth = datebirth;
    }

    public Date getDatedeath() {
        return datedeath;
    }

    public void setDatedeath(Date datedeath) {
        this.datedeath = datedeath;
    }

    public Cemetery getCemetery() {
        return cemetery;
    }

    public void setCemetery(Cemetery cemetery) {
        this.cemetery = cemetery;
    }

    public String getGraveLoc() {
        return graveLoc;
    }

    public void setGraveLoc(String graveLoc) {
        this.graveLoc = graveLoc;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getVitaPath() {
        return vitaPath;
    }

    public void setVitaPath(String vitaPath) {
        this.vitaPath = vitaPath;
    }

    public String getTombstonePath() {
        return tombstonePath;
    }

    public void setTombstonePath(String tombstonePath) {
        this.tombstonePath = tombstonePath;
    }
}