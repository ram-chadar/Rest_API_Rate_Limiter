package com.vasyerp.service;

import java.time.LocalDateTime;

class UserRequestInfo {
        private LocalDateTime startTime;
        private int requestCount;

        public UserRequestInfo() {
            this.startTime = LocalDateTime.now();
            this.requestCount = 0;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public void incrementRequestCount() {
            this.requestCount++;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }
    }