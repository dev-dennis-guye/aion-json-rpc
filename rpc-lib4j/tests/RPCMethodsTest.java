package org.aion.api.server.rpc3;
//TODO clean up the imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import java.math.BigInteger;
import org.aion.crypto.HashUtil;
import org.aion.rpc.errors.RPCExceptions;
import org.aion.rpc.types.RPCTypes.AddressParams;
import org.aion.rpc.types.RPCTypes.ByteArray;
import org.aion.rpc.types.RPCTypes.MinerStats;
import org.aion.rpc.types.RPCTypes.ParamUnion;
import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.ResultUnion;
import org.aion.rpc.types.RPCTypes.SubmitBlockParams;
import org.aion.rpc.types.RPCTypes.VersionType;
import org.aion.rpc.types.RPCTypesConverter.AionAddressConverter;
import org.aion.rpc.types.RPCTypesConverter.RequestConverter;
import org.aion.rpc.types.RPCTypes.BlockEnum;
import org.aion.rpc.types.RPCTypes.BlockSpecifier;
import org.aion.rpc.types.RPCTypes.BlockSpecifierUnion;
import org.aion.rpc.types.RPCTypes.ByteArray;
import org.aion.rpc.types.RPCTypes.ParamUnion;
import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.ResultUnion;
import org.aion.rpc.types.RPCTypes.VersionType;
import org.aion.rpc.types.RPCTypesConverter.BlockDetailsConverter;
import org.aion.rpc.types.RPCTypesConverter.BlockSpecifierConverter;
import org.aion.types.AionAddress;
import org.aion.types.Log;
import org.aion.util.types.ByteArrayWrapper;
import org.aion.zero.impl.types.AionBlock;
import org.aion.zero.impl.types.AionTxInfo;
import org.aion.zero.impl.types.StakingBlock;
import org.aion.zero.impl.types.StakingBlockHeader;
import java.math.BigInteger;
import org.aion.crypto.HashUtil;
import org.aion.rpc.errors.RPCExceptions;
import org.aion.rpc.types.RPCTypes.AddressParams;
import org.aion.rpc.types.RPCTypes.ByteArray;
import org.aion.rpc.types.RPCTypes.MinerStats;
import org.aion.rpc.types.RPCTypes.ParamUnion;
import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.ResultUnion;
import org.aion.rpc.types.RPCTypes.SubmitBlockParams;
import org.aion.rpc.types.RPCTypes.VersionType;
import org.aion.rpc.types.RPCTypesConverter.AionAddressConverter;
import org.aion.rpc.types.RPCTypesConverter.RequestConverter;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import java.util.Arrays;
import org.aion.rpc.errors.RPCExceptions.UnsupportedUnityFeatureRPCException;
import org.aion.rpc.types.RPCTypes.ByteArray;
import org.aion.rpc.types.RPCTypes.ParamUnion;
import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.SubmitSeedParams;
import org.aion.rpc.types.RPCTypes.SubmitSignatureParams;
import org.aion.rpc.types.RPCTypes.VersionType;
import org.aion.rpc.types.RPCTypes.VoidParams;
import org.aion.types.AionAddress;
import org.aion.zero.impl.blockchain.AionImpl;
import org.junit.Before;
import org.junit.Test;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-11-22
*
*****************************************************************************/
public class RPCMethodsTest{
    private RPCTestUtilsInterface testUtils= RPCTestUtils.getUtils();
    public void personal_ecRecoverTest(){
        String method = "personal_ecRecover";
        Request successfulRequest = testUtils.personal_ecRecover_succeeds();
        ResultUnion resultUnion = testUtils.personal_ecRecoverRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void getseedTestErrorsUnsupportedUnityFeatureRPCException(){
        String method = "getseed";
        try{
            Request request = testUtils.getseed_UnsupportedUnityFeature();
            testUtils.getseedRPC().execute(request);
            fail();
        }catch(UnsupportedUnityFeatureRPCException e){/*We expect this error*/}
    }

    public void getseedTest(){
        String method = "getseed";
        Request successfulRequest = testUtils.getseed_succeeds();
        ResultUnion resultUnion = testUtils.getseedRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void submitseedTestErrorsUnsupportedUnityFeatureRPCException(){
        String method = "submitseed";
        try{
            Request request = testUtils.submitseed_UnsupportedUnityFeature();
            testUtils.submitseedRPC().execute(request);
            fail();
        }catch(UnsupportedUnityFeatureRPCException e){/*We expect this error*/}
    }

    public void submitseedTest(){
        String method = "submitseed";
        Request successfulRequest = testUtils.submitseed_succeeds();
        ResultUnion resultUnion = testUtils.submitseedRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void submitsignatureTestErrorsUnsupportedUnityFeatureRPCException(){
        String method = "submitsignature";
        try{
            Request request = testUtils.submitsignature_UnsupportedUnityFeature();
            testUtils.submitsignatureRPC().execute(request);
            fail();
        }catch(UnsupportedUnityFeatureRPCException e){/*We expect this error*/}
    }

    public void submitsignatureTestErrorsBlockTemplateNotFoundRPCException(){
        String method = "submitsignature";
        try{
            Request request = testUtils.submitsignature_BlockTemplateNotFound();
            testUtils.submitsignatureRPC().execute(request);
            fail();
        }catch(BlockTemplateNotFoundRPCException e){/*We expect this error*/}
    }

    public void submitsignatureTest(){
        String method = "submitsignature";
        Request successfulRequest = testUtils.submitsignature_succeeds();
        ResultUnion resultUnion = testUtils.submitsignatureRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void ops_getBlockDetailsTest(){
        String method = "ops_getBlockDetails";
        Request successfulRequest = testUtils.ops_getBlockDetails_succeeds();
        ResultUnion resultUnion = testUtils.ops_getBlockDetailsRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void getblocktemplateTest(){
        String method = "getblocktemplate";
        Request successfulRequest = testUtils.getblocktemplate_succeeds();
        ResultUnion resultUnion = testUtils.getblocktemplateRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void submitblockTestErrorsBlockTemplateNotFoundRPCException(){
        String method = "submitblock";
        try{
            Request request = testUtils.submitblock_BlockTemplateNotFound();
            testUtils.submitblockRPC().execute(request);
            fail();
        }catch(BlockTemplateNotFoundRPCException e){/*We expect this error*/}
    }

    public void submitblockTestErrorsFailedToSealBlockRPCException(){
        String method = "submitblock";
        try{
            Request request = testUtils.submitblock_FailedToSealBlock();
            testUtils.submitblockRPC().execute(request);
            fail();
        }catch(FailedToSealBlockRPCException e){/*We expect this error*/}
    }

    public void submitblockTest(){
        String method = "submitblock";
        Request successfulRequest = testUtils.submitblock_succeeds();
        ResultUnion resultUnion = testUtils.submitblockRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void validateaddressTest(){
        String method = "validateaddress";
        Request successfulRequest = testUtils.validateaddress_succeeds();
        ResultUnion resultUnion = testUtils.validateaddressRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void getDifficultyTest(){
        String method = "getDifficulty";
        Request successfulRequest = testUtils.getDifficulty_succeeds();
        ResultUnion resultUnion = testUtils.getDifficultyRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void getMinerStatsTestErrorsFailedToComputeMetricsRPCException(){
        String method = "getMinerStats";
        try{
            Request request = testUtils.getMinerStats_FailedToComputeMetrics();
            testUtils.getMinerStatsRPC().execute(request);
            fail();
        }catch(FailedToComputeMetricsRPCException e){/*We expect this error*/}
    }

    public void getMinerStatsTest(){
        String method = "getMinerStats";
        Request successfulRequest = testUtils.getMinerStats_succeeds();
        ResultUnion resultUnion = testUtils.getMinerStatsRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void pingTest(){
        String method = "ping";
        Request successfulRequest = testUtils.ping_succeeds();
        ResultUnion resultUnion = testUtils.pingRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void ops_getAccountStateTest(){
        String method = "ops_getAccountState";
        Request successfulRequest = testUtils.ops_getAccountState_succeeds();
        ResultUnion resultUnion = testUtils.ops_getAccountStateRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void ops_getTransactionTest(){
        String method = "ops_getTransaction";
        Request successfulRequest = testUtils.ops_getTransaction_succeeds();
        ResultUnion resultUnion = testUtils.ops_getTransactionRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void ops_getBlockDetailsByNumberTest(){
        String method = "ops_getBlockDetailsByNumber";
        Request successfulRequest = testUtils.ops_getBlockDetailsByNumber_succeeds();
        ResultUnion resultUnion = testUtils.ops_getBlockDetailsByNumberRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void ops_getBlockDetailsByHashTest(){
        String method = "ops_getBlockDetailsByHash";
        Request successfulRequest = testUtils.ops_getBlockDetailsByHash_succeeds();
        ResultUnion resultUnion = testUtils.ops_getBlockDetailsByHashRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void personal_unlockAccountTest(){
        String method = "personal_unlockAccount";
        Request successfulRequest = testUtils.personal_unlockAccount_succeeds();
        ResultUnion resultUnion = testUtils.personal_unlockAccountRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void personal_lockAccountTest(){
        String method = "personal_lockAccount";
        Request successfulRequest = testUtils.personal_lockAccount_succeeds();
        ResultUnion resultUnion = testUtils.personal_lockAccountRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void personal_newAccountTest(){
        String method = "personal_newAccount";
        Request successfulRequest = testUtils.personal_newAccount_succeeds();
        ResultUnion resultUnion = testUtils.personal_newAccountRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
    public void personal_listAccountsTest(){
        String method = "personal_listAccounts";
        Request successfulRequest = testUtils.personal_listAccounts_succeeds();
        ResultUnion resultUnion = testUtils.personal_listAccountsRPC().execute(request);
        assertNotNull(resultUnion);
        resultUnion.encode();
    }
}
