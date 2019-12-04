package org.aion.rpc.server;

import static org.aion.rpc.errors.RPCExceptions.*;

import java.math.BigInteger;
import java.util.*;
import org.aion.rpc.types.RPCTypes.*;
import org.aion.rpc.types.RPCTypesConverter.*;
import org.aion.types.AionAddress;
import org.aion.util.types.ByteArrayWrapper;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-12-10
*
*****************************************************************************/
public interface RPCServerMethods extends RPC{
    /**
    *
    * @param request the client request
    * @param rpc the rpc implementation to be used in fulfilling this request.
    * @throws InvalidParamsRPCException if the parameters are not well formed
    * @throws NullReturnRPCException if the specified method returns a null result
    * @throws MethodNotFoundRPCException if the requested method does not exist
    * @return the result of this request
    */
    static Object execute(Request request, RPCServerMethods rpc){
        Object res;
            //check that the request can be fulfilled by this class
            if(request.method.equals("personal_ecRecover")){
                EcRecoverParams params= EcRecoverParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                AionAddress result = rpc.personal_ecRecover(params.dataThatWasSigned,params.signature);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for personal_ecRecover");
                else res = AddressConverter.encode(result);
            }else
            if(request.method.equals("getseed")){
                VoidParams params= VoidParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                ByteArray result = rpc.getseed();
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for getseed");
                else res = DataHexStringConverter.encode(result);
            }else
            if(request.method.equals("submitseed")){
                SubmitSeedParams params= SubmitSeedParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                ByteArray result = rpc.submitseed(params.newSeed,params.signingPublicKey,params.coinbase);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for submitseed");
                else res = DataHexStringConverter.encode(result);
            }else
            if(request.method.equals("submitsignature")){
                SubmitSignatureParams params= SubmitSignatureParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                Boolean result = rpc.submitsignature(params.signature,params.sealHash);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for submitsignature");
                else res = BoolConverter.encode(result);
            }else
            if(request.method.equals("ops_getBlockDetails")){
                BlockSpecifierParams params= BlockSpecifierParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BlockDetails result = rpc.ops_getBlockDetails(params.block);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for ops_getBlockDetails");
                else res = BlockDetailsConverter.encode(result);
            }else
            if(request.method.equals("getBlockTemplate")){
                VoidParams params= VoidParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BlockTemplate result = rpc.getBlockTemplate();
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for getBlockTemplate");
                else res = BlockTemplateConverter.encode(result);
            }else
            if(request.method.equals("submitBlock")){
                SubmitBlockParams params= SubmitBlockParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                SubmissionResult result = rpc.submitBlock(params.nonce,params.solution,params.headerHash);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for submitBlock");
                else res = SubmissionResultConverter.encode(result);
            }else
            if(request.method.equals("validateaddress")){
                AddressParams params= AddressParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                ValidateAddressResult result = rpc.validateaddress(params.address);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for validateaddress");
                else res = ValidateAddressResultConverter.encode(result);
            }else
            if(request.method.equals("getDifficulty")){
                VoidParams params= VoidParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BigInteger result = rpc.getDifficulty();
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for getDifficulty");
                else res = Uint128HexStringConverter.encode(result);
            }else
            if(request.method.equals("getMinerStatistics")){
                AddressParams params= AddressParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                MinerStats result = rpc.getMinerStatistics(params.address);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for getMinerStatistics");
                else res = MinerStatsConverter.encode(result);
            }else
            if(request.method.equals("ping")){
                VoidParams params= VoidParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                PongEnum result = rpc.ping();
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for ping");
                else res = PongEnumConverter.encode(result);
            }else
            if(request.method.equals("ops_getAccountState")){
                AddressParams params= AddressParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                AccountState result = rpc.ops_getAccountState(params.address);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for ops_getAccountState");
                else res = AccountStateConverter.encode(result);
            }else
            if(request.method.equals("ops_getTransaction")){
                TransactionHashParams params= TransactionHashParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                OpsTransaction result = rpc.ops_getTransaction(params.hash);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for ops_getTransaction");
                else res = OpsTransactionConverter.encode(result);
            }else
            if(request.method.equals("ops_getBlockDetailsByNumber")){
                BlockNumberParams params= BlockNumberParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BlockDetails result = rpc.ops_getBlockDetailsByNumber(params.block);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for ops_getBlockDetailsByNumber");
                else res = BlockDetailsConverter.encode(result);
            }else
            if(request.method.equals("ops_getBlockDetailsByHash")){
                BlockHashParams params= BlockHashParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BlockDetails result = rpc.ops_getBlockDetailsByHash(params.block);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for ops_getBlockDetailsByHash");
                else res = BlockDetailsConverter.encode(result);
            }else
            if(request.method.equals("eth_getBalance")){
                AddressBlockParams params= AddressBlockParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BigInteger result = rpc.eth_getBalance(params.address,params.block);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_getBalance");
                else res = Uint256HexStringConverter.encode(result);
            }else
            if(request.method.equals("eth_getTransactionCount")){
                AddressBlockParams params= AddressBlockParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                BigInteger result = rpc.eth_getTransactionCount(params.address,params.block);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_getTransactionCount");
                else res = Uint128HexStringConverter.encode(result);
            }else
            if(request.method.equals("personal_unlockAccount")){
                UnlockAccountParams params= UnlockAccountParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                Boolean result = rpc.personal_unlockAccount(params.address,params.password,params.duration);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for personal_unlockAccount");
                else res = BoolConverter.encode(result);
            }else
            if(request.method.equals("personal_lockAccount")){
                LockAccountParams params= LockAccountParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                Boolean result = rpc.personal_lockAccount(params.address,params.password);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for personal_lockAccount");
                else res = BoolConverter.encode(result);
            }else
            if(request.method.equals("personal_newAccount")){
                PasswordParams params= PasswordParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                AionAddress result = rpc.personal_newAccount(params.password);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for personal_newAccount");
                else res = AddressConverter.encode(result);
            }else
            if(request.method.equals("personal_listAccounts")){
                VoidParams params= VoidParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                AionAddress[] result = rpc.personal_listAccounts();
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for personal_listAccounts");
                else res = AddressListConverter.encode(result);
            }else
            if(request.method.equals("eth_blockNumber")){
                VoidParams params= VoidParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                Long result = rpc.eth_blockNumber();
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_blockNumber");
                else res = LongConverter.encode(result);
            }else
            if(request.method.equals("eth_call")){
                CallParams params= CallParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                ByteArray result = rpc.eth_call(params.transaction,params.block);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_call");
                else res = DataHexStringConverter.encode(result);
            }else
            if(request.method.equals("eth_syncing")){
                VoidParams params= VoidParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                SyncInfoUnion result = rpc.eth_syncing();
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_syncing");
                else res = SyncInfoUnionConverter.encode(result);
            }else
            if(request.method.equals("eth_sendRawTransaction")){
                SendTransactionRawParams params= SendTransactionRawParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                ByteArray result = rpc.eth_sendRawTransaction(params.transaction);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_sendRawTransaction");
                else res = Byte32StringConverter.encode(result);
            }else
            if(request.method.equals("eth_sendTransaction")){
                SendTransactionParams params= SendTransactionParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                ByteArray result = rpc.eth_sendTransaction(params.transaction);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_sendTransaction");
                else res = Byte32StringConverter.encode(result);
            }else
            if(request.method.equals("eth_getTransactionByHash")){
                TransactionHashParams params= TransactionHashParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                EthTransaction result = rpc.eth_getTransactionByHash(params.hash);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_getTransactionByHash");
                else res = EthTransactionConverter.encode(result);
            }else
            if(request.method.equals("eth_getTransactionReceipt")){
                TransactionHashParams params= TransactionHashParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                EthTransactionReceipt result = rpc.eth_getTransactionReceipt(params.hash);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_getTransactionReceipt");
                else res = EthTransactionReceiptConverter.encode(result);
            }else
            if(request.method.equals("eth_getBlockByNumber")){
                EthBlockNumberParams params= EthBlockNumberParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                EthBlock result = rpc.eth_getBlockByNumber(params.block,params.fullTransaction);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_getBlockByNumber");
                else res = EthBlockConverter.encode(result);
            }else
            if(request.method.equals("eth_getBlockByHash")){
                EthBlockHashParams params= EthBlockHashParamsConverter.decode(request.params);
                if (params==null) throw InvalidParamsRPCException.INSTANCE;
                EthBlock result = rpc.eth_getBlockByHash(params.block,params.fullTransaction);
                // JSON RPC spec demands that if the result member does not exist
                // we should return an error see: https://www.jsonrpc.org/specification#response_object
                if (result == null ) throw new NullReturnRPCException("Could not retrieve a result for eth_getBlockByHash");
                else res = EthBlockConverter.encode(result);
            }else
                throw MethodNotFoundRPCException.INSTANCE;
        return res;
    }

    /**
    * @return a set containing all the methods supported by this interface
    */
    static Set<String> listMethods(){
        return Set.of( "personal_ecRecover", "getseed", "submitseed", "submitsignature", "ops_getBlockDetails", "getBlockTemplate", "submitBlock", "validateaddress", "getDifficulty", "getMinerStatistics", "ping", "ops_getAccountState", "ops_getTransaction", "ops_getBlockDetailsByNumber", "ops_getBlockDetailsByHash", "eth_getBalance", "eth_getTransactionCount", "personal_unlockAccount", "personal_lockAccount", "personal_newAccount", "personal_listAccounts", "eth_blockNumber", "eth_call", "eth_syncing", "eth_sendRawTransaction", "eth_sendTransaction", "eth_getTransactionByHash", "eth_getTransactionReceipt", "eth_getBlockByNumber", "eth_getBlockByHash");
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
    BlockTemplate getBlockTemplate();
    /**
    * 
    * @param nonce 
    * @param solution 
    * @param headerHash 


    * @return 
    */
    SubmissionResult submitBlock(ByteArray nonce,ByteArray solution,ByteArray headerHash);
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
    MinerStats getMinerStatistics(AionAddress address);
    /**
    * 
    * @return 
    */
    PongEnum ping();
    /**
    * 
    * @param address 


    * @return 
    */
    AccountState ops_getAccountState(AionAddress address);
    /**
    * 
    * @param hash 


    * @return 
    */
    OpsTransaction ops_getTransaction(ByteArray hash);
    /**
    * 
    * @param block 


    * @return 
    */
    BlockDetails ops_getBlockDetailsByNumber(Long block);
    /**
    * 
    * @param block 


    * @return 
    */
    BlockDetails ops_getBlockDetailsByHash(ByteArray block);
    /**
    * The account balance for the specified account at a given block height 
    * 
    * @param address An Aion account address

    * @param block Specifies the block to be returned with either a block hash, number or
                    enum.
                



    * @return 
    */
    BigInteger eth_getBalance(AionAddress address,BlockNumberEnumUnion block);
    /**
    * The account balance for the specified account at a given block height 
    * 
    * @param address An Aion account address

    * @param block Specifies the block to be returned with either a block hash, number or
                    enum.
                



    * @return 
    */
    BigInteger eth_getTransactionCount(AionAddress address,BlockNumberEnumUnion block);
    /**
    * 
    * @param address 
    * @param password 
    * @param duration 


    * @return 
    */
    Boolean personal_unlockAccount(AionAddress address,String password,Integer duration);
    /**
    * 
    * @param address 
    * @param password 


    * @return 
    */
    Boolean personal_lockAccount(AionAddress address,String password);
    /**
    * 
    * @param password 


    * @return 
    */
    AionAddress personal_newAccount(String password);
    /**
    * 
    * @return 
    */
    AionAddress[] personal_listAccounts();
    /**
    * Returns the block number of the last block added to the chain.
    * 
    * @return 
    */
    Long eth_blockNumber();
    /**
    * 
    * @param transaction 
    * @param block 


    * @return 
    */
    ByteArray eth_call(TxCall transaction,BlockNumberEnumUnion block);
    /**
    * Used to determine if the kernel is up-to-date with the rest of the network.
    * 
    * @return Details the current sync state of the kernel.

    */
    SyncInfoUnion eth_syncing();
    /**
    * Executes a transaction to be sealed in a block and returns the transaction hash of the created transaction.
    * 
    * @param transaction 


    * @return 
    */
    ByteArray eth_sendRawTransaction(ByteArray transaction);
    /**
    * Executes a transaction to be sealed in a block and returns the transaction hash of the created transaction.
    * 
    * @param transaction 


    * @return 
    */
    ByteArray eth_sendTransaction(TxCall transaction);
    /**
    * 
    * @param hash 


    * @return 
    */
    EthTransaction eth_getTransactionByHash(ByteArray hash);
    /**
    * 
    * @param hash 


    * @return 
    */
    EthTransactionReceipt eth_getTransactionReceipt(ByteArray hash);
    /**
    * 
    * @param block 
    * @param fullTransaction 


    * @return 
    */
    EthBlock eth_getBlockByNumber(Long block,Boolean fullTransaction);
    /**
    * 
    * @param block 
    * @param fullTransaction 


    * @return 
    */
    EthBlock eth_getBlockByHash(ByteArray block,Boolean fullTransaction);

    /**
    * @return an map that stores the method names as the key and the interface(namespace) as the value.
    */
    static Map<String, String> methodInterfaceMap(){
        return Map.ofEntries(
            Map.entry("personal_ecRecover", "personal"),
            Map.entry("getseed", "stratum"),
            Map.entry("submitseed", "stratum"),
            Map.entry("submitsignature", "stratum"),
            Map.entry("ops_getBlockDetails", "ops"),
            Map.entry("getBlockTemplate", "stratum"),
            Map.entry("submitBlock", "stratum"),
            Map.entry("validateaddress", "stratum"),
            Map.entry("getDifficulty", "stratum"),
            Map.entry("getMinerStatistics", "stratum"),
            Map.entry("ping", ""),
            Map.entry("ops_getAccountState", "ops"),
            Map.entry("ops_getTransaction", "ops"),
            Map.entry("ops_getBlockDetailsByNumber", "ops"),
            Map.entry("ops_getBlockDetailsByHash", "ops"),
            Map.entry("eth_getBalance", "eth"),
            Map.entry("eth_getTransactionCount", "eth"),
            Map.entry("personal_unlockAccount", "personal"),
            Map.entry("personal_lockAccount", "personal"),
            Map.entry("personal_newAccount", "personal"),
            Map.entry("personal_listAccounts", "personal"),
            Map.entry("eth_blockNumber", "eth"),
            Map.entry("eth_call", "eth"),
            Map.entry("eth_syncing", "eth"),
            Map.entry("eth_sendRawTransaction", "eth"),
            Map.entry("eth_sendTransaction", "eth"),
            Map.entry("eth_getTransactionByHash", "eth"),
            Map.entry("eth_getTransactionReceipt", "eth"),
            Map.entry("eth_getBlockByNumber", "eth"),
            Map.entry("eth_getBlockByHash", "eth")
        );
    }

}
