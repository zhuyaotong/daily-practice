package com.github.zhuyaotong.designpatterns.oop.hyperemiamodel.anemiamodel;

import java.math.BigDecimal;

class VirtualWalletController {
    // 通过构造函数或者IOC框架注入
//    private VirtualWalletService virtualWalletService;
//
//    public BigDecimal getBalance(Long walletId) { ...} //查询余额
//
//    public void debit(Long walletId, BigDecimal amount) { ...} //出账
//
//    public void credit(Long walletId, BigDecimal amount) { ...} //入账
//
//    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) { ...} //转账
    //省略查询transaction的接口
}

class VirtualWalletBo {//省略getter/setter/constructor方法
    private Long id;
    private Long createTime;
    private BigDecimal balance;
}

enum TransactionType {
    DEBIT,
    CREDIT,
    TRANSFER;
}

//class VirtualWalletService {
//    // 通过构造函数或者IOC框架注入
//    private VirtualWalletRepository walletRepo;
//    private VirtualWalletTransactionRepository transactionRepo;
//
//    public VirtualWalletBo getVirtualWallet(Long walletId) {
//        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
//        VirtualWalletBo walletBo = convert(walletEntity);
//        return walletBo;
//    }
//
//    public BigDecimal getBalance(Long walletId) {
//        return walletRepo.getBalance(walletId);
//    }
//
//    @Transactional
//    public void debit(Long walletId, BigDecimal amount) {
//        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
//        BigDecimal balance = walletEntity.getBalance();
//        if (balance.compareTo(amount) < 0) {
//            throw new NoSufficientBalanceException(...);
//        }
//        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
//        transactionEntity.setAmount(amount);
//        transactionEntity.setCreateTime(System.currentTimeMillis());
//        transactionEntity.setType(TransactionType.DEBIT);
//        transactionEntity.setFromWalletId(walletId);
//        transactionRepo.saveTransaction(transactionEntity);
//        walletRepo.updateBalance(walletId, balance.subtract(amount));
//    }
//
//    @Transactional
//    public void credit(Long walletId, BigDecimal amount) {
//        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
//        transactionEntity.setAmount(amount);
//        transactionEntity.setCreateTime(System.currentTimeMillis());
//        transactionEntity.setType(TransactionType.CREDIT);
//        transactionEntity.setFromWalletId(walletId);
//        transactionRepo.saveTransaction(transactionEntity);
//        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
//        BigDecimal balance = walletEntity.getBalance();
//        walletRepo.updateBalance(walletId, balance.add(amount));
//    }
//
//    @Transactional
//    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
//        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
//        transactionEntity.setAmount(amount);
//        transactionEntity.setCreateTime(System.currentTimeMillis());
//        transactionEntity.setType(TransactionType.TRANSFER);
//        transactionEntity.setFromWalletId(fromWalletId);
//        transactionEntity.setToWalletId(toWalletId);
//        transactionRepo.saveTransaction(transactionEntity);
//        debit(fromWalletId, amount);
//        credit(toWalletId, amount);
//    }
//}
