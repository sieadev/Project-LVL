package dev.siea.projectlvl.models;

public class LevelData {

    private String uuid;
    private double exp;
    private int speed;
    private int health;
    private int oxygen;
    private int damage;
    private int jumpingForce;
    private int harvesting;
    private int fireResistance;
    public LevelData(String uuid, double exp, int speed, int health, int oxygen, int damage, int jumpingForce, int harvesting, int fireResistance) {
        this.uuid = uuid;
        this.exp = exp;
        this.speed = speed;
        this.health = health;
        this.oxygen = oxygen;
        this.damage = damage;
        this.jumpingForce = jumpingForce;
        this.harvesting = harvesting;
        this.fireResistance = fireResistance;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getJumpingForce() {
        return jumpingForce;
    }

    public void setJumpingForce(int jumpingForce) {
        this.jumpingForce = jumpingForce;
    }

    public int getHarvesting() {
        return harvesting;
    }

    public void setHarvesting(int harvesting) {
        this.harvesting = harvesting;
    }

    public int getFireResistance() {
        return fireResistance;
    }

    public void setFireResistance(int fireResistance) {
        this.fireResistance = fireResistance;
    }
}
