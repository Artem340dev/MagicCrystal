package me.peoplexrcb.magiccrystal.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BlockBooleanMetadata {
    @Getter
    String key;

    @Getter
    BlockBooleanMetadataValue value;

    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class BlockBooleanMetadataValue implements MetadataValue {
        Plugin plugin;
        boolean value;

        @Nullable
        @Override
        public Object value() {
            return value;
        }

        @Override
        public int asInt() {
            return 0;
        }

        @Override
        public float asFloat() {
            return 0;
        }

        @Override
        public double asDouble() {
            return 0;
        }

        @Override
        public long asLong() {
            return 0;
        }

        @Override
        public short asShort() {
            return 0;
        }

        @Override
        public byte asByte() {
            return 0;
        }

        @Override
        public boolean asBoolean() {
            return value;
        }

        @NotNull
        @Override
        public String asString() {
            return null;
        }

        @Nullable
        @Override
        public Plugin getOwningPlugin() {
            return plugin;
        }

        @Override
        public void invalidate() {
        }
    }
}