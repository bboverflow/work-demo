package cn.trusteye.work.mongo;

import cn.trusteye.work.pojo.Message;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/28 18:04
 * @Modified By:
 */
public class MessageCodec implements Codec<Message>{
    @Override
    public Message decode(BsonReader reader, DecoderContext decoderContext) {
        Message message=new Message();
        reader.readStartDocument();
        message.setId(reader.readObjectId("_id").toString());
        message.setServer_no(reader.readString("SERVER_NO"));
        message.setSrc_ip(reader.readInt64("SRC_IP"));
        message.setDst_ip(reader.readInt64("DST_IP"));
        message.setPartition_time(new Date(reader.readDateTime("PARTISION_TIME")));

        return null;
    }

    @Override
    public void encode(BsonWriter writer, Message message, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeObjectId("_id",new ObjectId(message.getId()));
        writer.writeString("SERVER_NO", message.getServer_no());
        writer.writeInt64("SRC_IP", message.getSrc_ip());
        writer.writeInt64("DST_IP", message.getDst_ip());
        writer.writeDateTime("PARTITION_TIME",message.getPartition_time().getTime());
        writer.writeEndDocument();
    }

    @Override
    public Class getEncoderClass() {
        return Message.class;
    }
}
