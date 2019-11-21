package org.aion.rpc.client;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import org.aion.rpc.types.RPCTypes.*;
import org.aion.rpc.types.RPCTypesConverter.*;
import org.aion.types.AionAddress;
import org.aion.util.types.ByteArrayWrapper;

/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-11-21
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

        return provider.execute(request, AionAddressConverter::decode);
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

        return provider.execute(request, BooleanConverter::decode);
    }
    /**
    * Returns the details of the specified block.
    * 
    * @param block Specifies the block to be returned with either a block hash, number or
                    enum.
                



    * @return 
    */
    public final BlockDetails ops_getBlockDetails(BlockSpecifierUnion block){
        BlockSpecifier params= new BlockSpecifier(block);
        Request request = new Request(generator.generateID(), "ops_getBlockDetails", BlockSpecifierConverter.encode(params), VersionType.Version2);

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

        return provider.execute(request, BigIntegerHexStringConverter::decode);
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
    * Returns the key used to sign an input string.
    * 
    * @param dataThatWasSigned 
    * @param signature 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> personal_ecRecover(ByteArray dataThatWasSigned,ByteArray signature, BiFunction<AionAddress, RPCError, O> asyncTask){
        EcRecoverParams params= new EcRecoverParams(dataThatWasSigned ,signature);
        Request request = new Request(generator.generateID(), "personal_ecRecover", EcRecoverParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, AionAddressConverter::decode, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getseed( BiFunction<ByteArray, RPCError, O> asyncTask){
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
    public final <O> CompletableFuture<O> submitseed(ByteArray newSeed,ByteArray signingPublicKey,AionAddress coinbase, BiFunction<ByteArray, RPCError, O> asyncTask){
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
    public final <O> CompletableFuture<O> submitsignature(ByteArray signature,ByteArray sealHash, BiFunction<Boolean, RPCError, O> asyncTask){
        SubmitSignatureParams params= new SubmitSignatureParams(signature ,sealHash);
        Request request = new Request(generator.generateID(), "submitsignature", SubmitSignatureParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BooleanConverter::decode, asyncTask);
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
    public final <O> CompletableFuture<O> ops_getBlockDetails(BlockSpecifierUnion block, BiFunction<BlockDetails, RPCError, O> asyncTask){
        BlockSpecifier params= new BlockSpecifier(block);
        Request request = new Request(generator.generateID(), "ops_getBlockDetails", BlockSpecifierConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BlockDetailsConverter::decode, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getblocktemplate( BiFunction<BlockTemplate, RPCError, O> asyncTask){
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
    public final <O> CompletableFuture<O> submitblock(ByteArray nonce,ByteArray solution,ByteArray headerHash, BiFunction<SubmissionResult, RPCError, O> asyncTask){
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
    public final <O> CompletableFuture<O> validateaddress(AionAddress address, BiFunction<ValidateAddressResult, RPCError, O> asyncTask){
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
    public final <O> CompletableFuture<O> getDifficulty( BiFunction<BigInteger, RPCError, O> asyncTask){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getDifficulty", VoidParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, BigIntegerHexStringConverter::decode, asyncTask);
    }
    /**
    * 
    * @param address 


    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getMinerStats(AionAddress address, BiFunction<MinerStats, RPCError, O> asyncTask){
        AddressParams params= new AddressParams(address);
        Request request = new Request(generator.generateID(), "getMinerStats", AddressParamsConverter.encode(params), VersionType.Version2);

        return provider.executeAsync(request, MinerStatsConverter::decode, asyncTask);
    }
}
