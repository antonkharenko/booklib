package com.antonkharenko.booklib.repository;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;

public class EmbedMongoFactory {

    private static MongodProcess mongoProcess;
    private static MongodExecutable mongoExecutable;

    public static MongodProcess getMongoProcess(int port) throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build();

        mongoExecutable = starter.prepare(mongodConfig);
        mongoProcess = mongoExecutable.start();

        return mongoProcess;
    }

    public static void stopMongoProcess() {
        if (mongoProcess != null)
            mongoProcess.stop();
        if (mongoExecutable != null)
            mongoExecutable.stop();
    }
}
