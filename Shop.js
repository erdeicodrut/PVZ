class Shop {

    constructor(balance) {
        this.loadout = [];
        this.balance = balance;
    }

    addItem(item) {
        this.loadout.push(item);
    }

    addBalance(balance) {
        this.balance += balance;
    }

    extract(value) {
        if (value <= this.balance) {
            this.balance -= value;
            return true;
        }
        return false;
    }
}
