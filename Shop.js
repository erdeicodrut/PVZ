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
}
