public class Car {
    private String color;
    private boolean isSportCar;
    private ModelOfCar modelOfCar;
    private int maxSpeed;

    public Car(String color, boolean isSportCar, ModelOfCar modelOfCar, int maxSpeed) {
        this.color = color;
        this.isSportCar = isSportCar;
        this.modelOfCar = modelOfCar;
        this.maxSpeed = maxSpeed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSportCar() {
        return isSportCar;
    }

    public void setSportCar(boolean sportCar) {
        isSportCar = sportCar;
    }

    public ModelOfCar getModelOfCar() {
        return modelOfCar;
    }

    public void setModelOfCar(ModelOfCar modelOfCar) {
        this.modelOfCar = modelOfCar;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", isSportCar=" + isSportCar +
                ", modelOfCar=" + modelOfCar +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
