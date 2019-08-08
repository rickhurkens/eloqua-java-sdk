package com.eloqua.api.bulk.exception;

public class EloquaSyncFailedException extends Throwable {

    private String failedSyncedInstanceUri;

    public EloquaSyncFailedException(String syncedInstanceUri) {
        this.failedSyncedInstanceUri = syncedInstanceUri;
    }

    @Override public String getMessage() {
        return "Sync failed for Uri: '" + failedSyncedInstanceUri + "'";
    }
}
