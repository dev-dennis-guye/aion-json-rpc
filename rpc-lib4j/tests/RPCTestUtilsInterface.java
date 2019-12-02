package org.aion.api.server.rpc3;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-12-10
*
*****************************************************************************/
public interface RPCTestUtilsInterface{
    Request personal_ecRecover_succeeds();
    RPC personal_ecRecoverRPC();
    Request getseed_UnsupportedUnityFeature()();
    Request getseed_succeeds();
    RPC getseedRPC();
    Request submitseed_UnsupportedUnityFeature()();
    Request submitseed_succeeds();
    RPC submitseedRPC();
    Request submitsignature_UnsupportedUnityFeature()();
    Request submitsignature_BlockTemplateNotFound()();
    Request submitsignature_succeeds();
    RPC submitsignatureRPC();
    Request ops_getBlockDetails_succeeds();
    RPC ops_getBlockDetailsRPC();
    Request getBlockTemplate_succeeds();
    RPC getBlockTemplateRPC();
    Request submitBlock_BlockTemplateNotFound()();
    Request submitBlock_FailedToSealBlock()();
    Request submitBlock_succeeds();
    RPC submitBlockRPC();
    Request validateaddress_succeeds();
    RPC validateaddressRPC();
    Request getDifficulty_succeeds();
    RPC getDifficultyRPC();
    Request getMinerStatistics_FailedToComputeMetrics()();
    Request getMinerStatistics_succeeds();
    RPC getMinerStatisticsRPC();
    Request ping_succeeds();
    RPC pingRPC();
    Request ops_getAccountState_succeeds();
    RPC ops_getAccountStateRPC();
    Request ops_getTransaction_succeeds();
    RPC ops_getTransactionRPC();
    Request ops_getBlockDetailsByNumber_succeeds();
    RPC ops_getBlockDetailsByNumberRPC();
    Request ops_getBlockDetailsByHash_succeeds();
    RPC ops_getBlockDetailsByHashRPC();
    Request eth_getBalance_succeeds();
    RPC eth_getBalanceRPC();
    Request eth_getTransactionCount_succeeds();
    RPC eth_getTransactionCountRPC();
    Request personal_unlockAccount_succeeds();
    RPC personal_unlockAccountRPC();
    Request personal_lockAccount_succeeds();
    RPC personal_lockAccountRPC();
    Request personal_newAccount_succeeds();
    RPC personal_newAccountRPC();
    Request personal_listAccounts_succeeds();
    RPC personal_listAccountsRPC();
    Request eth_blockNumber_succeeds();
    RPC eth_blockNumberRPC();
    Request eth_call_NullReturn()();
    Request eth_call_succeeds();
    RPC eth_callRPC();
}
