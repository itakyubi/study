package com.wa.leetcode;

/**
 * Bank
 * https://leetcode-cn.com/problems/simple-bank-system/
 * 2022-03-18 09:04
 *
 * @author wuao
 */
public class Bank {

    private long[] balance;

    public Bank(long[] balance) {
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!checkAccount(account1) || !checkAccount(account2) || balance[account1 - 1] < money) {
            return false;
        }

        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (!checkAccount(account)) {
            return false;
        }

        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (!checkAccount(account) || balance[account - 1] < money) {
            return false;
        }

        balance[account - 1] -= money;
        return true;
    }

    private boolean checkAccount(int account) {
        return account >= 1 && account <= balance.length;
    }
}
