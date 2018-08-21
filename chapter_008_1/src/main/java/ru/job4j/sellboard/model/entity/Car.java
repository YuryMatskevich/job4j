package ru.job4j.sellboard.model.entity;

import ru.job4j.sellboard.model.entity.enums.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Yury Matskevich
 */
@Entity
@Table(name = "car")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_c")
	private int id;

	@Column(name = "age_c")
	private int age;

	@Column(name = "way_c")
	private int way;

	@Enumerated(EnumType.STRING)
	@Column(name = "sort_c")
	private Sort sort;

	@Enumerated(EnumType.STRING)
	@Column(name = "transmission_c")
	private Transmission transmission;

	@Enumerated(EnumType.STRING)
	@Column(name = "manipulator_c")
	private Manipulator manipulator;

	@Enumerated(EnumType.STRING)
	@Column(name = "engine_c")
	private Engine engine;

	@Enumerated(EnumType.STRING)
	@Column(name = "frame_c")
	private Frame frame;

	@OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
	private Ad ad;

	public Car() {
	}

	public Car(
			int age,
			int way,
			Sort sort,
			Transmission transmission,
			Manipulator manipulator,
			Engine engine,
			Frame frame) {
		this.age = age;
		this.way = way;
		this.sort = sort;
		this.transmission = transmission;
		this.manipulator = manipulator;
		this.engine = engine;
		this.frame = frame;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public Manipulator getManipulator() {
		return manipulator;
	}

	public void setManipulator(Manipulator manipulator) {
		this.manipulator = manipulator;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public Ad getAds() {
		return ad;
	}

	public void setAds(Ad ad) {
		this.ad = ad;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Car car = (Car) o;
		return age == car.age
				&& way == car.way
				&& sort == car.sort
				&& transmission == car.transmission
				&& manipulator == car.manipulator
				&& engine == car.engine
				&& frame == car.frame;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, way, sort, transmission, manipulator, engine, frame);
	}
}
