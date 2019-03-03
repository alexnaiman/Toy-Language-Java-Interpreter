package Model.Threads.CyclicBarrier;

import Model.Utils.IntForkGenerator;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class BarrierData {
    private Integer key;
    private List<Integer> list = new ArrayList<>();

    public BarrierData(Integer key) {
        this.key = key;
    }

    public BarrierData(Integer key, List<Integer> list) {
        this.key = key;
        this.list = list;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        for(Integer a: list) {
            buff.append(a.toString()).append(',');
        }
        return "" + key + "->{" + buff.toString() + "}\n";
    }
    public String getListString(){
        StringBuilder buff = new StringBuilder();
        for(Integer a: list) {
            buff.append(a.toString()).append(',');
        }
        return "{" + buff.toString() + "}";
    }
}
