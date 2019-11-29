package org.aion.rpc.client;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import org.aion.rpc.types.RPCTypes.*;
import org.aion.rpc.types.RPCTypesConverter.*;
import org.aion.types.AionAddress;
import java.util.*;
import org.aion.util.types.ByteArrayWrapper;

/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-11-29
*
*****************************************************************************/
public class RPCClientMethods{

    private final Provider provider;
    private final IDGeneratorStrategy generator;

    public RPCClientMethods(final Provider provider, IDGeneratorStrategy generator){
        this.provider = provider;
        this.generator = generator;
    }
    /**
    * Returns the key used to sign an input string.
    * 
    * @param dataThatWasSigned 
    * @param signature 


    * @return 
    */
    public final AionAddress personal_ecRecover(ByteArray dataThatWasSigned,ByteArray signature){
        EcRecoverParams params= new EcRecoverParams(dataThatWasSigned ,signature);
        Request request = new Request(generator.generateID(), "personal_ecRecover", EcRecoverParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, AddressConverter::decode);
    }
    /**
    * 
    * @return 
    */
    public final ByteArray getseed(){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getseed", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, DataHexStringConverter::decode);
    }
    /**
    * 
    * @param newSeed 
    * @param signingPublicKey 
    * @param coinbase 


    * @return 
    */
    public final ByteArray submitseed(ByteArray newSeed,ByteArray signingPublicKey,AionAddress coinbase){
        SubmitSeedParams params= new SubmitSeedParams(newSeed ,signingPublicKey ,coinbase);
        Request request = new Request(generator.generateID(), "submitseed", SubmitSeedParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, DataHexStringConverter::decode);
    }
    /**
    * 
    * @param signature 
    * @param sealHash 


    * @return 
    */
    public final Boolean submitsignature(ByteArray signature,ByteArray sealHash){
        SubmitSignatureParams params= new SubmitSignatureParams(signature ,sealHash);
        Request request = new Request(generator.generateID(), "submitsignature", SubmitSignatureParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, BoolConverter::decode);
    }
    /**
    * Returns the details of the specified block.
    * 
    * @param block Specifies the block to be returned with either a block hash, number or
                    enum.
                



    * @return 
    */
    public final BlockDetails ops_getBlockDetails(BlockSpecifierUnion block){
        BlockSpecifierParams params= new BlockSpecifierParams(block);
        Request request = new Request(generator.generateID(), "ops_getBlockDetails", BlockSpecifierParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, BlockDetailsConverter::decode);
    }
    /**
    * 
    * @return 
    */
    public final BlockTemplate getblocktemplate(){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getblocktemplate", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, BlockTemplateConverter::decode);
    }
    /**
    * 
    * @param nonce 
    * @param solution 
    * @param headerHash 


    * @return 
    */
    public final SubmissionResult submitblock(ByteArray nonce,ByteArray solution,ByteArray headerHash){
        SubmitBlockParams params= new SubmitBlockParams(nonce ,solution ,headerHash);
        Request request = new Request(generator.generateID(), "submitblock", SubmitBlockParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, SubmissionResultConverter::decode);
    }
    /**
    * 
    * @param address 


    * @return 
    */
    public final ValidateAddressResult validateaddress(AionAddress address){
        AddressParams params= new AddressParams(address);
        Request request = new Request(generator.generateID(), "validateaddress", AddressParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, ValidateAddressResultConverter::decode);
    }
    /**
    * 
    * @return 
    */
    public final BigInteger getDifficulty(){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getDifficulty", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, BigIntHexStringConverter::decode);
    }
    /**
    * 
    * @param address 


    * @return 
    */
    public final MinerStats getMinerStats(AionAddress address){
        AddressParams params= new AddressParams(address);
        Request request = new Request(generator.generateID(), "getMinerStats", AddressParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, MinerStatsConverter::decode);
    }
    /**
    * 
    * @return 
    */
    public final PongEnum ping(){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "ping", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, PongEnumConverter::decode);
    }
    /**
    * 
    * @param address 


    * @return 
    */
    public final AccountState ops_getAccountState(AionAddress address){
        AddressParams params= new AddressParams(address);
        Request request = new Request(generator.generateID(), "ops_getAccountState", AddressParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, AccountStateConverter::decode);
    }
    /**
    * 
    * @param hash 


    * @return 
    */
    public final OpsTransaction ops_getTransaction(ByteArray hash){
        TransactionHashParams params= new TransactionHashParams(hash);
        Request request = new Request(generator.generateID(), "ops_getTransaction", TransactionHashParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, OpsTransactionConverter::decode);
    }
    /**
    * 
    * @param block 


    * @return 
    */
    public final BlockDetails ops_getBlockDetailsByNumber(Long block){
        BlockNumberParams params= new BlockNumberParams(block);
        Request request = new Request(generator.generateID(), "ops_getBlockDetailsByNumber", BlockNumberParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, BlockDetailsConverter::decode);
    }
    /**
    * 
    * @param block 


    * @return 
    */
    public final BlockDetails ops_getBlockDetailsByHash(ByteArray block){
        BlockHashParams params= new BlockHashParams(block);
        Request request = new Request(generator.generateID(), "ops_getBlockDetailsByHash", BlockHashParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, BlockDetailsConverter::decode);
    }
    /**
    * 
    * @param address 
    * @param password 
    * @param duration 


    * @return 
    */
    public final Boolean personal_unlockAccount(AionAddress address,String password,Integer duration){
        UnlockAccountParams params= new UnlockAccountParams(address ,password ,duration);
        Request request = new Request(generator.generateID(), "personal_unlockAccount", UnlockAccountParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, BoolConverter::decode);
    }
    /**
    * 
    * @param address 
    * @param password 


    * @return 
    */
    public final Boolean personal_lockAccount(AionAddress address,String password){
        LockAccountParams params= new LockAccountParams(address ,password);
        Request request = new Request(generator.generateID(), "personal_lockAccount", LockAccountParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, BoolConverter::decode);
    }
    /**
    * 
    * @param password 


    * @return 
    */
    public final AionAddress personal_newAccount(String password){
        PasswordParams params= new PasswordParams(password);
        Request request = new Request(generator.generateID(), "personal_newAccount", PasswordParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, AddressConverter::decode);
    }
    /**
    * 
    * @return 
    */
    public final List<AionAddress> personal_listAccounts(){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "personal_listAccounts", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.execute(request, AddressListConverter::decode);
    }
    /**
    * Returns the key used to sign an input string.
    * 
    * @param dataThatWasSigned 
    * @param signature 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> personal_ecRecover(ByteArray dataThatWasSigned,ByteArray signature, BiFunction<AionAddress, RpcError, O> asyncTask){
        EcRecoverParams params= new EcRecoverParams(dataThatWasSigned ,signature);
        Request request = new Request(generator.generateID(), "personal_ecRecover", EcRecoverParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, AddressConverter::decode, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getseed( BiFunction<ByteArray, RpcError, O> asyncTask){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getseed", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, DataHexStringConverter::decode, asyncTask);
    }
    /**
    * 
    * @param newSeed 
    * @param signingPublicKey 
    * @param coinbase 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> submitseed(ByteArray newSeed,ByteArray signingPublicKey,AionAddress coinbase, BiFunction<ByteArray, RpcError, O> asyncTask){
        SubmitSeedParams params= new SubmitSeedParams(newSeed ,signingPublicKey ,coinbase);
        Request request = new Request(generator.generateID(), "submitseed", SubmitSeedParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, DataHexStringConverter::decode, asyncTask);
    }
    /**
    * 
    * @param signature 
    * @param sealHash 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> submitsignature(ByteArray signature,ByteArray sealHash, BiFunction<Boolean, RpcError, O> asyncTask){
        SubmitSignatureParams params= new SubmitSignatureParams(signature ,sealHash);
        Request request = new Request(generator.generateID(), "submitsignature", SubmitSignatureParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BoolConverter::decode, asyncTask);
    }
    /**
    * Returns the details of the specified block.
    * 
    * @param block Specifies the block to be returned with either a block hash, number or
                    enum.
                



    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> ops_getBlockDetails(BlockSpecifierUnion block, BiFunction<BlockDetails, RpcError, O> asyncTask){
        BlockSpecifierParams params= new BlockSpecifierParams(block);
        Request request = new Request(generator.generateID(), "ops_getBlockDetails", BlockSpecifierParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BlockDetailsConverter::decode, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getblocktemplate( BiFunction<BlockTemplate, RpcError, O> asyncTask){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getblocktemplate", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BlockTemplateConverter::decode, asyncTask);
    }
    /**
    * 
    * @param nonce 
    * @param solution 
    * @param headerHash 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> submitblock(ByteArray nonce,ByteArray solution,ByteArray headerHash, BiFunction<SubmissionResult, RpcError, O> asyncTask){
        SubmitBlockParams params= new SubmitBlockParams(nonce ,solution ,headerHash);
        Request request = new Request(generator.generateID(), "submitblock", SubmitBlockParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, SubmissionResultConverter::decode, asyncTask);
    }
    /**
    * 
    * @param address 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> validateaddress(AionAddress address, BiFunction<ValidateAddressResult, RpcError, O> asyncTask){
        AddressParams params= new AddressParams(address);
        Request request = new Request(generator.generateID(), "validateaddress", AddressParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, ValidateAddressResultConverter::decode, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getDifficulty( BiFunction<BigInteger, RpcError, O> asyncTask){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getDifficulty", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BigIntHexStringConverter::decode, asyncTask);
    }
    /**
    * 
    * @param address 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getMinerStats(AionAddress address, BiFunction<MinerStats, RpcError, O> asyncTask){
        AddressParams params= new AddressParams(address);
        Request request = new Request(generator.generateID(), "getMinerStats", AddressParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, MinerStatsConverter::decode, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> ping( BiFunction<PongEnum, RpcError, O> asyncTask){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "ping", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, PongEnumConverter::decode, asyncTask);
    }
    /**
    * 
    * @param address 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> ops_getAccountState(AionAddress address, BiFunction<AccountState, RpcError, O> asyncTask){
        AddressParams params= new AddressParams(address);
        Request request = new Request(generator.generateID(), "ops_getAccountState", AddressParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, AccountStateConverter::decode, asyncTask);
    }
    /**
    * 
    * @param hash 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> ops_getTransaction(ByteArray hash, BiFunction<OpsTransaction, RpcError, O> asyncTask){
        TransactionHashParams params= new TransactionHashParams(hash);
        Request request = new Request(generator.generateID(), "ops_getTransaction", TransactionHashParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, OpsTransactionConverter::decode, asyncTask);
    }
    /**
    * 
    * @param block 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> ops_getBlockDetailsByNumber(Long block, BiFunction<BlockDetails, RpcError, O> asyncTask){
        BlockNumberParams params= new BlockNumberParams(block);
        Request request = new Request(generator.generateID(), "ops_getBlockDetailsByNumber", BlockNumberParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BlockDetailsConverter::decode, asyncTask);
    }
    /**
    * 
    * @param block 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> ops_getBlockDetailsByHash(ByteArray block, BiFunction<BlockDetails, RpcError, O> asyncTask){
        BlockHashParams params= new BlockHashParams(block);
        Request request = new Request(generator.generateID(), "ops_getBlockDetailsByHash", BlockHashParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BlockDetailsConverter::decode, asyncTask);
    }
    /**
    * 
    * @param address 
    * @param password 
    * @param duration 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> personal_unlockAccount(AionAddress address,String password,Integer duration, BiFunction<Boolean, RpcError, O> asyncTask){
        UnlockAccountParams params= new UnlockAccountParams(address ,password ,duration);
        Request request = new Request(generator.generateID(), "personal_unlockAccount", UnlockAccountParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BoolConverter::decode, asyncTask);
    }
    /**
    * 
    * @param address 
    * @param password 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> personal_lockAccount(AionAddress address,String password, BiFunction<Boolean, RpcError, O> asyncTask){
        LockAccountParams params= new LockAccountParams(address ,password);
        Request request = new Request(generator.generateID(), "personal_lockAccount", LockAccountParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BoolConverter::decode, asyncTask);
    }
    /**
    * 
    * @param password 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> personal_newAccount(String password, BiFunction<AionAddress, RpcError, O> asyncTask){
        PasswordParams params= new PasswordParams(password);
        Request request = new Request(generator.generateID(), "personal_newAccount", PasswordParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, AddressConverter::decode, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> personal_listAccounts( BiFunction<List<AionAddress>, RpcError, O> asyncTask){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "personal_listAccounts", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, AddressListConverter::decode, asyncTask);
    }
}
