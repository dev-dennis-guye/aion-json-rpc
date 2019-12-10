package org.aion.rpc.constants;
import java.util.List;
import java.util.NoSuchElementException;

/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-12-11
*
*****************************************************************************/
public enum Method{
    Personal_ecRecover("personal_ecRecover", "personal"),
    Getseed("getseed", "stratum"),
    Submitseed("submitseed", "stratum"),
    Submitsignature("submitsignature", "stratum"),
    Ops_getBlockDetails("ops_getBlockDetails", "ops"),
    GetBlockTemplate("getBlockTemplate", "stratum"),
    SubmitBlock("submitBlock", "stratum"),
    Validateaddress("validateaddress", "stratum"),
    GetDifficulty("getDifficulty", "stratum"),
    GetMinerStatistics("getMinerStatistics", "stratum"),
    Ping("ping", ""),
    Ops_getAccountState("ops_getAccountState", "ops"),
    Ops_getTransaction("ops_getTransaction", "ops"),
    Ops_getBlockDetailsByNumber("ops_getBlockDetailsByNumber", "ops"),
    Ops_getBlockDetailsByHash("ops_getBlockDetailsByHash", "ops"),
    Eth_getBalance("eth_getBalance", "eth"),
    Eth_getTransactionCount("eth_getTransactionCount", "eth"),
    Personal_unlockAccount("personal_unlockAccount", "personal"),
    Personal_lockAccount("personal_lockAccount", "personal"),
    Personal_newAccount("personal_newAccount", "personal"),
    Personal_listAccounts("personal_listAccounts", "personal"),
    Eth_blockNumber("eth_blockNumber", "eth"),
    Eth_call("eth_call", "eth"),
    Eth_syncing("eth_syncing", "eth"),
    Eth_sendRawTransaction("eth_sendRawTransaction", "eth"),
    Eth_sendTransaction("eth_sendTransaction", "eth"),
    Eth_getTransactionByHash("eth_getTransactionByHash", "eth"),
    Eth_getTransactionReceipt("eth_getTransactionReceipt", "eth"),
    Eth_getBlockByNumber("eth_getBlockByNumber", "eth"),
    Eth_getBlockByHash("eth_getBlockByHash", "eth");
    /**
    * The name used to specify this method within the RPC.
    */
    public final String name;
    /**
    * The namespace to which this method belongs.
    */
    public final String namespace;
    private static final List<Method> values = List.of(Method.values());
    private Method(String name, String namespace){
        this.name=name;
        this.namespace = namespace;
    }

    /**
    * Returns the method constant using the specified method name
    * @param name the method name
    * @throws NoSuchElementException if a constant for the specified name could now be found
    * @return the method constant
    */
    public static Method constantFromName(String name){
        for(Method method: Method.values){
            if(method.name.equals(name)) return method;
        }
        throw new NoSuchElementException();
    }
}