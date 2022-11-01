package com.rahulg.datatypes;

// Simple Enum with fixed set of objects.
enum OrderStatus {
    ORDERED,
    PREPARATION,
    READY,
    ON_THE_WAY,
    DELIVERED,
    REJECTED

}

class Pizza {

    private String type;
    OrderStatus status; // using the ENUM here to store the current status

    Pizza(String type) {
        this.type = type;
        this.status = OrderStatus.ORDERED;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public boolean isDelivered() {
        return getStatus() == OrderStatus.DELIVERED;
    }

    public int getDeliveryTimeInDays() {
        switch (status) {
            case ORDERED: return 5;
            case READY: return 2;
            case DELIVERED: return 0;
        }
        return 0;
    }

}

public class SimpleEmunExample {
    public static void main(String[] args) {
        for(PizzaStatus status : PizzaStatus.values()) {
            System.out.println("PizzaStsus:" + status.name() + ":" + status.toString() + ":" + status.getTimeToDelivery());
        }

        PizzaStatus status = PizzaStatus.valueOf("ORDERED");
        System.out.println("Ordered:" + status.name() + ":" + status.toString() + ":" + status.getTimeToDelivery());
    }
}


// ENUM with instance variable and methods.
enum PizzaStatus {
    ORDERED (5){
        @Override
        public boolean isOrdered() {
            return true;
        }
    },
    READY (2){
        @Override
        public boolean isReady() {
            return true;
        }
    },
    DELIVERED (0){
        @Override
        public boolean isDelivered() {
            return true;
        }
    };

    private int timeToDelivery;

    public boolean isOrdered() {return false;}

    public boolean isReady() {return false;}

    public boolean isDelivered(){return false;}

    public int getTimeToDelivery() {
        return timeToDelivery;
    }

    // ENUM constructors.
    PizzaStatus (int timeToDelivery) {
        this.timeToDelivery = timeToDelivery;
    }
}


// ENUM implemeting strategy pattern in JAVA
enum PizzaDeliveryStrategy {
    EXPRESS {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("Pizza will be delivered in express mode");
        }
    },
    NORMAL {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("Pizza will be delivered in normal mode:" + pz.getDeliveryTimeInDays());
        }
    };

    public abstract void deliver(Pizza pz);
}