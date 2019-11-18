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
* GENERATED: 2019-11-18
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
        Request request = new Request(generator.generateID(), "personal_ecRecover", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.address);
    }
    /**
    * 
    * @return 
    */
    public final ByteArray getseed(){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getseed", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.byteArray);
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
        Request request = new Request(generator.generateID(), "submitseed", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.byteArray);
    }
    /**
    * 
    * @param signature 
    * @param sealHash 


    * @return 
    */
    public final Boolean submitsignature(ByteArray signature,ByteArray sealHash){
        SubmitSignatureParams params= new SubmitSignatureParams(signature ,sealHash);
        Request request = new Request(generator.generateID(), "submitsignature", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.bool);
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
        Request request = new Request(generator.generateID(), "ops_getBlockDetails", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.blockDetails);
    }
    /**
    * 
    * @return 
    */
    public final BlockTemplate getblocktemplate(){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getblocktemplate", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.blockTemplate);
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
        Request request = new Request(generator.generateID(), "submitblock", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.submissionResult);
    }
    /**
    * 
    * @param address 


    * @return 
    */
    public final ValidateAddressResult validateaddress(AionAddress address){
        AddressParams params= new AddressParams(address);
        Request request = new Request(generator.generateID(), "validateaddress", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.validateAddressResult);
    }
    /**
    * 
    * @return 
    */
    public final BigInteger getDifficulty(){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getDifficulty", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.bigInt);
    }
    /**
    * 
    * @param address 


    * @return 
    */
    public final MinerStats getMinerStats(AionAddress address){
        AddressParams params= new AddressParams(address);
        Request request = new Request(generator.generateID(), "getMinerStats", new ParamUnion(params), VersionType.Version2);

        return provider.execute(request, r->r.minerStats);
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
        Request request = new Request(generator.generateID(), "personal_ecRecover", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.address, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getseed( BiFunction<ByteArray, RPCError, O> asyncTask){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getseed", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.byteArray, asyncTask);
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
        Request request = new Request(generator.generateID(), "submitseed", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.byteArray, asyncTask);
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
        Request request = new Request(generator.generateID(), "submitsignature", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.bool, asyncTask);
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
        Request request = new Request(generator.generateID(), "ops_getBlockDetails", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.blockDetails, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getblocktemplate( BiFunction<BlockTemplate, RPCError, O> asyncTask){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getblocktemplate", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.blockTemplate, asyncTask);
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
        Request request = new Request(generator.generateID(), "submitblock", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.submissionResult, asyncTask);
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
        Request request = new Request(generator.generateID(), "validateaddress", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.validateAddressResult, asyncTask);
    }
    /**
    * 
    * @param asyncTask The task to be executed upon successfuly or exceptional completion of this RPC request.
    * @param <O> the result type of the async task.
    * @return The result of this asynchronous request
    */
    public final <O> CompletableFuture<O> getDifficulty( BiFunction<BigInteger, RPCError, O> asyncTask){
        VoidParams params= new VoidParams();
        Request request = new Request(generator.generateID(), "getDifficulty", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.bigInt, asyncTask);
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
        Request request = new Request(generator.generateID(), "getMinerStats", new ParamUnion(params), VersionType.Version2);

        return provider.executeAsync(request, r->r.minerStats, asyncTask);
    }
}