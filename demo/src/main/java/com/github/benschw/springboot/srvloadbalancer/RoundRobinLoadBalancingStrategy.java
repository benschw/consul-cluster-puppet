package com.github.benschw.springboot.srvloadbalancer;

import com.google.common.net.HostAndPort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RoundRobinLoadBalancingStrategy implements LoadBalancingStrategy {
    private List<HostAndPort> nodes;
    private int index = 0;

    public HostAndPort next(List<HostAndPort> rawNodes) {
        List<HostAndPort> newNodes = normalize(rawNodes);
        refreshAndIncrement(newNodes);
        return current();
    }

    private HostAndPort current() {
        return nodes.get(index);
    }

    private List<HostAndPort> normalize(List<HostAndPort> rawNodes) {
        List newNodes = new ArrayList(rawNodes);
        Collections.sort(newNodes, new Comparator<HostAndPort>() {
            public int compare(HostAndPort x, HostAndPort y) {
                // TODO: Handle null x or y values
                return x.getHostText().compareTo(y.getHostText());
            }
        });
        return newNodes;
    }

    private void refreshAndIncrement(List<HostAndPort> newNodes) {
        if (hasChanges(newNodes)) {
            nodes = newNodes;
            index = 0;
        } else {
            if (index < nodes.size() - 1) {
                index++;
            } else {
                index = 0;
            }
        }
    }

    private boolean hasChanges(List<HostAndPort> newNodes) {
        if (nodes == null) {
            return true;
        }
        return nodes.size() != newNodes.size() || !nodes.containsAll(nodes);
    }
}
