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
* GENERATED: 2019-11-18
*
*****************************************************************************/
public interface RPCServerMethods extends RPC{
    /**
    *
    * @param request the client request
    * @param rpc the rpc implementation to be used in fulfilling this request.
    * @return the result of this request
    */
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

    /**
    * Returns the key used to sign an input string.
    * 
    * @param dataThatWasSigned 
    * @param signature 


    * @return 
    */
    AionAddress personal_ecRecover(ByteArray dataThatWasSigned,ByteArray signature);
    /**
    * 
    * @return 
    */
    ByteArray getseed();
    /**
    * 
    * @param newSeed 
    * @param signingPublicKey 
    * @param coinbase 


    * @return 
    */
    ByteArray submitseed(ByteArray newSeed,ByteArray signingPublicKey,AionAddress coinbase);
    /**
    * 
    * @param signature 
    * @param sealHash 


    * @return 
    */
    Boolean submitsignature(ByteArray signature,ByteArray sealHash);
    /**
    * Returns the details of the specified block.
    * 
    * @param block Specifies the block to be returned with either a block hash, number or
                    enum.
                



    * @return 
    */
    BlockDetails ops_getBlockDetails(BlockSpecifierUnion block);
    /**
    * 
    * @return 
    */
    BlockTemplate getblocktemplate();
    /**
    * 
    * @param nonce 
    * @param solution 
    * @param headerHash 


    * @return 
    */
    SubmissionResult submitblock(ByteArray nonce,ByteArray solution,ByteArray headerHash);
    /**
    * 
    * @param address 


    * @return 
    */
    ValidateAddressResult validateaddress(AionAddress address);
    /**
    * 
    * @return 
    */
    BigInteger getDifficulty();
    /**
    * 
    * @param address 


    * @return 
    */
    MinerStats getMinerStats(AionAddress address);
}
