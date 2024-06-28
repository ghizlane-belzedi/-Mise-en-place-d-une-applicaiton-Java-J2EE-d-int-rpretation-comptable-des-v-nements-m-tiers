package ma.inetum.brique.model.principale;

import ma.inetum.brique.model.pojo.VehicleFinalisationHeaderPOJO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CS_ME_CDFIH")
public class VehicleFinalisationHeaderP extends VehicleFinalisationHeaderPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="VEHICLE_FINALISATION_HEAD_SEQ")
    @SequenceGenerator(name="VEHICLE_FINALISATION_HEAD_SEQ",sequenceName="VEHICLE_FINALISATION_HEAD_SEQ", allocationSize=1)
    @Column(name = "ID")
    private Long    id;
    @Column(name= "MAGIC")
    private	Long	magic;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CHARGEMENT")
    private Chargement chargement;
    @Column(name = "DATE_CHARGEMENT")
    private LocalDateTime dateChargement;
    @Column(name= "LOADED")
    private Boolean loaded;
    @Column(name= "DATE_ENVOI")
    private LocalDateTime dateEnvoi;
    @Column(name= "SENT")
    private Boolean sent;
    @OneToMany(mappedBy = "vehicleFinalisationHeader", cascade = CascadeType.ALL)
    private List<VehicleFinalisationDetailsP> vehicleFinalisationDetailsP = new ArrayList<>();

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Long getMagic() {return magic;}

    public void setMagic(Long magic) {this.magic = magic;}

    public Chargement getChargement() {return chargement;}

    public void setChargement(Chargement chargement) {this.chargement = chargement;}

    public LocalDateTime getDateChargement() {return dateChargement;}

    public void setDateChargement(LocalDateTime dateChargement) {this.dateChargement = dateChargement;}

    public Boolean getLoaded() {return loaded;}

    public void setLoaded(Boolean loaded) {this.loaded = loaded;}

    public LocalDateTime getDateEnvoi() {return dateEnvoi;}

    public void setDateEnvoi(LocalDateTime dateEnvoi) {this.dateEnvoi = dateEnvoi;}

    public Boolean getSent() {return sent;}

    public void setSent(Boolean sent) {this.sent = sent;}

    public List<VehicleFinalisationDetailsP> getVehicleFinalisationDetailsP() {return vehicleFinalisationDetailsP;}

    public void setVehicleFinalisationDetailsP(List<VehicleFinalisationDetailsP> vehicleFinalisationDetailsP) {this.vehicleFinalisationDetailsP = vehicleFinalisationDetailsP;}

}
