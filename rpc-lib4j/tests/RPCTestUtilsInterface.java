package org.aion.api.server.rpc3;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-11-19
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
    Request getblocktemplate_succeeds();
    RPC getblocktemplateRPC();
    Request submitblock_BlockTemplateNotFound()();
    Request submitblock_FailedToSealBlock()();
    Request submitblock_succeeds();
    RPC submitblockRPC();
    Request validateaddress_succeeds();
    RPC validateaddressRPC();
    Request getDifficulty_succeeds();
    RPC getDifficultyRPC();
    Request getMinerStats_FailedToComputeMetrics()();
    Request getMinerStats_succeeds();
    RPC getMinerStatsRPC();
}