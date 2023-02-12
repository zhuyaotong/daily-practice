package com.github.zhuyaotong.designpatterns.oop.hyperemiamodel;

import java.math.BigDecimal;

public class VirtualWallet { // Domain领域模型(充血模型)
    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public BigDecimal balance() {
        return this.balance;
    }

//    public void debit(BigDecimal amount) {
//        if (this.balance.compareTo(amount) < 0) {
//            throw new InsufficientBalanceException(...);
//        }
//        this.balance = this.balance.subtract(amount);
//    }

//    public void credit(BigDecimal amount) {
//        if (amount.compareTo(BigDecimal.ZERO) < 0) {
//            throw new InvalidAmountException(...);
//        }
//        this.balance = this.balance.add(amount);
//    }
}

//public class VirtualWalletService {
//    // 通过构造函数或者IOC框架注入
//    private VirtualWalletRepository walletRepo;
//    private VirtualWalletTransactionRepository transactionRepo;
//
//    public VirtualWallet getVirtualWallet(Long walletId) {
//        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
//        VirtualWallet wallet = convert(walletEntity);
//        return wallet;
//    }
//
//    public BigDecimal getBalance(Long walletId) {
//        return walletRepo.getBalance(walletId);
//    }
//
//    @Transactional
//    public void debit(Long walletId, BigDecimal amount) {
//        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
//        VirtualWallet wallet = convert(walletEntity);
//        wallet.debit(amount);
//        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
//        transactionEntity.setAmount(amount);
//        transactionEntity.setCreateTime(System.currentTimeMillis());
//        transactionEntity.setType(TransactionType.DEBIT);
//        transactionEntity.setFromWalletId(walletId);
//        transactionRepo.saveTransaction(transactionEntity);
//        walletRepo.updateBalance(walletId, wallet.balance());
//    }
//
//    @Transactional
//    public void credit(Long walletId, BigDecimal amount) {
//        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
//        VirtualWallet wallet = convert(walletEntity);
//        wallet.credit(amount);
//        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
//        transactionEntity.setAmount(amount);
//        transactionEntity.setCreateTime(System.currentTimeMillis());
//        transactionEntity.setType(TransactionType.CREDIT);
//        transactionEntity.setFromWalletId(walletId);
//        transactionRepo.saveTransaction(transactionEntity);
//        walletRepo.updateBalance(walletId, wallet.balance());
//    }
//
//    @Transactional
//    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
//        //...跟基于贫血模型的传统开发模式的代码一样...
//    }
//}

//public class VirtualWallet {
//    private Long id;
//    private Long createTime = System.currentTimeMillis();;
//    private BigDecimal balance = BigDecimal.ZERO;
//    private boolean isAllowedOverdraft = true;
//    private BigDecimal overdraftAmount = BigDecimal.ZERO;
//    private BigDecimal frozenAmount = BigDecimal.ZERO;
//
//    public VirtualWallet(Long preAllocatedId) {
//        this.id = preAllocatedId;
//    }
//
//    public void freeze(BigDecimal amount) { ... }
//    public void unfreeze(BigDecimal amount) { ...}
//    public void increaseOverdraftAmount(BigDecimal amount) { ... }
//    public void decreaseOverdraftAmount(BigDecimal amount) { ... }
//    public void closeOverdraft() { ... }
//    public void openOverdraft() { ... }
//
//    public BigDecimal balance() {
//        return this.balance;
//    }
//
//    public BigDecimal getAvaliableBalance() {
//        BigDecimal totalAvaliableBalance = this.balance.subtract(this.frozenAmount);
//        if (isAllowedOverdraft) {
//            totalAvaliableBalance += this.overdraftAmount;
//        }
//        return totalAvaliableBalance;
//    }
//
//    public void debit(BigDecimal amount) {
//        BigDecimal totalAvaliableBalance = getAvaliableBalance();
//        if (totoalAvaliableBalance.compareTo(amount) < 0) {
//            throw new InsufficientBalanceException(...);
//        }
//        this.balance = this.balance.subtract(amount);
//    }
//
//    public void credit(BigDecimal amount) {
//        if (amount.compareTo(BigDecimal.ZERO) < 0) {
//            throw new InvalidAmountException(...);
//        }
//        this.balance = this.balance.add(amount);
//    }
//}
