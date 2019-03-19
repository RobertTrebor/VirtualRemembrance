package de.lengsfeld.vr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
    private Long id;

    @Basic(optional = false)
    @Column(name = "FIRSTNAME")
    private String firstname;

    @Basic(optional = false)
    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "DATEBIRTH")
    @Temporal(TemporalType.DATE)
    private Date datebirth;

    @Column(name = "DATEDEATH")
    @Temporal(TemporalType.DATE)
    private Date datedeath;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_CEMETERY_ID"))
    private Cemetery cemetery;

    @Column(name = "GRAVE_LOC")
    private String graveLoc;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "LONGITUDE")
    private String longitude;

    @Column(name = "VITA_PATH")
    private String vitaPath;

    @Column(name = "TOMBSTONE_PATH")
    private String tombstonePath;

    @OneToMany(mappedBy = "grave")
    private List<Image> images;

    @Transient
    private String tombstonePathDir;


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

    public static String getFindAll() {
        return findAll;
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

    public String getGraveLoc() {
        return graveLoc;
    }

    public void setGraveLoc(String graveLoc) {
        this.graveLoc = graveLoc;
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

    public String getVitaPath() {
        return vitaPath;
    }

    public void setVitaPath(String vitaPath) {
        this.vitaPath = vitaPath;
    }

    public String getTombstonePath() {
        return this.tombstonePath;
    }

    public void setTombstonePath(String tombstonePath) {
        this.tombstonePath = tombstonePath;
    }

    public Cemetery getCemetery() {
        return cemetery;
    }

    public void setCemetery(Cemetery cemetery) {
        this.cemetery = cemetery;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Transient
    public String getTombstonePathDir() {
        String pattern = "([0-9]+)";
        StringBuilder stringBuilder = new StringBuilder("/resources/cemeteries/");
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(tombstonePath);
        if (m.find()) {
            stringBuilder.append(m.group(0));
            stringBuilder.append("/" + tombstonePath);
            String tombstonePathDir = stringBuilder.toString();
            return tombstonePathDir;
        }
        else return "/resources/img/noimage.jpg";
    }

    public void setTombstonePathDir(String tombstonePathDir) {
        this.tombstonePathDir = tombstonePathDir;
    }

}