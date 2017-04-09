class Shop {
    this.loadout = [];

    constructor(balance) {
        this.balance = balance;
    }

    this.addItem(item) {
        this.loadout.push(item);
    }

    this.addBalance(balance) {
        this.balance += balance;
    }
}
