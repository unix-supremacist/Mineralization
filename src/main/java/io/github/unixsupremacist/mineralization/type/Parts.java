package io.github.unixsupremacist.mineralization.type;

import io.github.unixsupremacist.mineralization.Mineralization;

import java.util.HashSet;
import java.util.List;

public class Parts {
    public HashSet<String> parts = new HashSet<>();

    public Parts(String... parts){
        this.parts.addAll(List.of(parts));
    }
}
