package com.space.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
public class Ship {
    public static final Integer CURRENT_YEAR = 3019;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //ID корабля

    private String name; //Название корабля (до 50 знаков включительно)
    private String planet; //Планета пребывания (до 50 знаков включительно)

    @Enumerated(EnumType.STRING)
    private ShipType shipType; //Тип корабля
    private Date prodDate; //Дата выпуска. Диапазон значений года 2800..3019 включительно
    private Boolean isUsed; //Использованный / новый
    private Double speed; //Максимальная скорость корабля. Диапазон значений 0,01..0,99 включительно. Используй математическое округление до сотых.
    private Integer crewSize; //Количество членов экипажа. Диапазон значений 1..9999 включительно.

    @Access(AccessType.PROPERTY)
    private Double rating; //Рейтинг корабля. Используй математическое округление до сотых.


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonSetter //Почему-то только при наличие этой аннотации RequestBody использует поступ к методам, а не к полям
    public void setName(String name) {
        if(name == null || name == "") throw new IllegalArgumentException("Error while setting name. Can't be null and empty");
        if(name.length() > 50) throw new IllegalArgumentException("Error while setting name. Can't be mere than 50 chars");
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    @JsonSetter //Почему-то только при наличие этой аннотации RequestBody использует поступ к методам, а не к полям
    public void setPlanet(String planet) {
        if(planet == null || planet == "") throw new IllegalArgumentException("Error while setting planet. Can't be null and empty");
        if(planet.length() > 50) throw new IllegalArgumentException("Error while setting planet. Can't be mere than 50 chars");
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    @JsonSetter //Почему-то только при наличие этой аннотации RequestBody использует поступ к методам, а не к полям
    public void setShipType(ShipType shipType) {
        if(shipType == null) throw new IllegalArgumentException("Error while setting shipType. Can't be null");
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    @JsonSetter //Почему-то только при наличие этой аннотации RequestBody использует поступ к методам, а не к полям
    public void setProdDate(Date prodDate) {
        if(prodDate == null) throw new IllegalArgumentException("Error while setting prodDate. Can't be null");
        if(prodDate.getTime() < 0) throw new IllegalArgumentException("Error while setting prodDate. Can't be < 0");

        Calendar shipProd = new GregorianCalendar();
        shipProd.setTime(prodDate);
        if(shipProd.get(Calendar.YEAR) < 2800 || shipProd.get(Calendar.YEAR) > 3019) throw new IllegalArgumentException("Error while setting prodDate. Value must be 2800..3019");

        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    @JsonSetter //Почему-то только при наличие этой аннотации RequestBody использует поступ к методам, а не к полям
    public void setUsed(Boolean used) {
        if (used == null) used = false;
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    @JsonSetter //Почему-то только при наличие этой аннотации RequestBody использует поступ к методам, а не к полям
    public void setSpeed(Double speed) {
        if(speed == null) throw new IllegalArgumentException("Error while setting speed. Value must be 0.01...0.99");
        Double newSpeed = Math.round(speed * 100.0)/100.0;
        if(newSpeed < 0.01 || newSpeed > 0.99) throw new IllegalArgumentException("Error while setting speed. Value must be 0.01...0.99");
        this.speed = newSpeed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    @JsonSetter //Почему-то только при наличие этой аннотации RequestBody использует поступ к методам, а не к полям
    public void setCrewSize(Integer crewSize) {
        if(crewSize == null || crewSize < 1 || crewSize > 9999) throw new IllegalArgumentException("Error while setting crewSize. Value must be 1...9999");
        this.crewSize = crewSize;
    }

    public Double getRating() {
        Double k = isUsed ? 0.5 : 1.0;
        Calendar shipProd = new GregorianCalendar();
        shipProd.setTime(prodDate);
        rating = (80.0 * speed * k)/(CURRENT_YEAR - shipProd.get(Calendar.YEAR) + 1.0);
        rating = Math.round(100.0 * rating) / 100.0;
        return rating;
    }

    @JsonSetter //Почему-то только при наличие этой аннотации RequestBody использует поступ к методам, а не к полям
    public void setRating(Double rating) {
        this.rating = rating;
    }
}
