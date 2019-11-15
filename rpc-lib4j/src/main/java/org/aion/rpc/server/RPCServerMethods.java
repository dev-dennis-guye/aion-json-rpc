package org.aion.rpc.server;

import static org.aion.rpc.errors.RPCExceptions.*;

import java.math.BigInteger;
import java.util.Set;
import org.aion.rpc.types.RPCTypes.*;
import org.aion.rpc.types.RPCTypesConverter.*;
import org.aion.types.AionAddress;
import org.aion.util.types.ByteArrayWrapper;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-11-15
*
*****************************************************************************/
public interface RPCServerMethods extends RPC{

    static ResultUnion execute(Request request, RPCServerMethods rpc){
        ResultUnion res;
            //check that the request can be fulfilled by this class
            if(request.method.equals("personal_ecRecover")){
                EcRecoverParams params= EcRecoverParamsConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                AionAddress result = rpc.personal_ecRecover(params.dataThatWasSigned,params.signature);
                res = result == null ? null : new ResultUnion(result);
            }else
            if(request.method.equals("getseed")){
                VoidParams params= VoidParamsConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                ByteArray result = rpc.getseed();
                res = result == null ? null : new ResultUnion(result);
            }else
            if(request.method.equals("submitseed")){
                SubmitSeedParams params= SubmitSeedParamsConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                ByteArray result = rpc.submitseed(params.newSeed,params.signingPublicKey,params.coinbase);
                res = result == null ? null : new ResultUnion(result);
            }else
            if(request.method.equals("submitsignature")){
                SubmitSignatureParams params= SubmitSignatureParamsConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                Boolean result = rpc.submitsignature(params.signature,params.sealHash);
                res = result == null ? null : new ResultUnion(result);
            }else
            if(request.method.equals("ops_getBlockDetails")){
                BlockSpecifier params= BlockSpecifierConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BlockDetails result = rpc.ops_getBlockDetails(params.block);
                res = result == null ? null : new ResultUnion(result);
            }else
            if(request.method.equals("getblocktemplate")){
                VoidParams params= VoidParamsConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BlockTemplate result = rpc.getblocktemplate();
                res = result == null ? null : new ResultUnion(result);
            }else
            if(request.method.equals("submitblock")){
                SubmitBlockParams params= SubmitBlockParamsConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                SubmissionResult result = rpc.submitblock(params.nonce,params.solution,params.headerHash);
                res = result == null ? null : new ResultUnion(result);
            }else
            if(request.method.equals("validateaddress")){
                AddressParams params= AddressParamsConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                ValidateAddressResult result = rpc.validateaddress(params.address);
                res = result == null ? null : new ResultUnion(result);
            }else
            if(request.method.equals("getDifficulty")){
                VoidParams params= VoidParamsConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BigInteger result = rpc.getDifficulty();
                res = result == null ? null : new ResultUnion(result);
            }else
            if(request.method.equals("getMinerStats")){
                AddressParams params= AddressParamsConverter.decode(request.params.encode());
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                MinerStats result = rpc.getMinerStats(params.address);
                res = result == null ? null : new ResultUnion(result);
            }else
                throw MethodNotFoundRPCException.INSTANCE;
        return res;
    }

    static Set<String> listMethods(){
        return Set.of( "personal_ecRecover", "getseed", "submitseed", "submitsignature", "ops_getBlockDetails", "getblocktemplate", "submitblock", "validateaddress", "getDifficulty", "getMinerStats");
    }

    AionAddress personal_ecRecover(ByteArray dataThatWasSigned,ByteArray signature);
    ByteArray getseed();
    ByteArray submitseed(ByteArray newSeed,ByteArray signingPublicKey,AionAddress coinbase);
    Boolean submitsignature(ByteArray signature,ByteArray sealHash);
    BlockDetails ops_getBlockDetails(BlockSpecifierUnion block);
    BlockTemplate getblocktemplate();
    SubmissionResult submitblock(ByteArray nonce,ByteArray solution,ByteArray headerHash);
    ValidateAddressResult validateaddress(AionAddress address);
    BigInteger getDifficulty();
    MinerStats getMinerStats(AionAddress address);
}
