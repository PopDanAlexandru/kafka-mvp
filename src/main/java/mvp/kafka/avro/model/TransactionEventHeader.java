/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package mvp.kafka.avro.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** Default information */
@org.apache.avro.specific.AvroGenerated
public class TransactionEventHeader extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4225349068153410712L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TransactionEventHeader\",\"namespace\":\"mvp.kafka.avro.model\",\"doc\":\"Default information\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"GUID (UUID) of the event making it globally identifiable\"},{\"name\":\"sourceSystem\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Source system which created the event\"},{\"name\":\"createdAt\",\"type\":\"long\",\"doc\":\"Event timestamp creation in epoch format\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TransactionEventHeader> ENCODER =
      new BinaryMessageEncoder<TransactionEventHeader>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TransactionEventHeader> DECODER =
      new BinaryMessageDecoder<TransactionEventHeader>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<TransactionEventHeader> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<TransactionEventHeader> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<TransactionEventHeader> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TransactionEventHeader>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this TransactionEventHeader to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a TransactionEventHeader from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a TransactionEventHeader instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static TransactionEventHeader fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** GUID (UUID) of the event making it globally identifiable */
  private java.lang.String id;
  /** Source system which created the event */
  private java.lang.String sourceSystem;
  /** Event timestamp creation in epoch format */
  private long createdAt;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TransactionEventHeader() {}

  /**
   * All-args constructor.
   * @param id GUID (UUID) of the event making it globally identifiable
   * @param sourceSystem Source system which created the event
   * @param createdAt Event timestamp creation in epoch format
   */
  public TransactionEventHeader(java.lang.String id, java.lang.String sourceSystem, java.lang.Long createdAt) {
    this.id = id;
    this.sourceSystem = sourceSystem;
    this.createdAt = createdAt;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return sourceSystem;
    case 2: return createdAt;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = value$ != null ? value$.toString() : null; break;
    case 1: sourceSystem = value$ != null ? value$.toString() : null; break;
    case 2: createdAt = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return GUID (UUID) of the event making it globally identifiable
   */
  public java.lang.String getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * GUID (UUID) of the event making it globally identifiable
   * @param value the value to set.
   */
  public void setId(java.lang.String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'sourceSystem' field.
   * @return Source system which created the event
   */
  public java.lang.String getSourceSystem() {
    return sourceSystem;
  }


  /**
   * Sets the value of the 'sourceSystem' field.
   * Source system which created the event
   * @param value the value to set.
   */
  public void setSourceSystem(java.lang.String value) {
    this.sourceSystem = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return Event timestamp creation in epoch format
   */
  public long getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * Event timestamp creation in epoch format
   * @param value the value to set.
   */
  public void setCreatedAt(long value) {
    this.createdAt = value;
  }

  /**
   * Creates a new TransactionEventHeader RecordBuilder.
   * @return A new TransactionEventHeader RecordBuilder
   */
  public static mvp.kafka.avro.model.TransactionEventHeader.Builder newBuilder() {
    return new mvp.kafka.avro.model.TransactionEventHeader.Builder();
  }

  /**
   * Creates a new TransactionEventHeader RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TransactionEventHeader RecordBuilder
   */
  public static mvp.kafka.avro.model.TransactionEventHeader.Builder newBuilder(mvp.kafka.avro.model.TransactionEventHeader.Builder other) {
    if (other == null) {
      return new mvp.kafka.avro.model.TransactionEventHeader.Builder();
    } else {
      return new mvp.kafka.avro.model.TransactionEventHeader.Builder(other);
    }
  }

  /**
   * Creates a new TransactionEventHeader RecordBuilder by copying an existing TransactionEventHeader instance.
   * @param other The existing instance to copy.
   * @return A new TransactionEventHeader RecordBuilder
   */
  public static mvp.kafka.avro.model.TransactionEventHeader.Builder newBuilder(mvp.kafka.avro.model.TransactionEventHeader other) {
    if (other == null) {
      return new mvp.kafka.avro.model.TransactionEventHeader.Builder();
    } else {
      return new mvp.kafka.avro.model.TransactionEventHeader.Builder(other);
    }
  }

  /**
   * RecordBuilder for TransactionEventHeader instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TransactionEventHeader>
    implements org.apache.avro.data.RecordBuilder<TransactionEventHeader> {

    /** GUID (UUID) of the event making it globally identifiable */
    private java.lang.String id;
    /** Source system which created the event */
    private java.lang.String sourceSystem;
    /** Event timestamp creation in epoch format */
    private long createdAt;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(mvp.kafka.avro.model.TransactionEventHeader.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.sourceSystem)) {
        this.sourceSystem = data().deepCopy(fields()[1].schema(), other.sourceSystem);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[2].schema(), other.createdAt);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing TransactionEventHeader instance
     * @param other The existing instance to copy.
     */
    private Builder(mvp.kafka.avro.model.TransactionEventHeader other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.sourceSystem)) {
        this.sourceSystem = data().deepCopy(fields()[1].schema(), other.sourceSystem);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[2].schema(), other.createdAt);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * GUID (UUID) of the event making it globally identifiable
      * @return The value.
      */
    public java.lang.String getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * GUID (UUID) of the event making it globally identifiable
      * @param value The value of 'id'.
      * @return This builder.
      */
    public mvp.kafka.avro.model.TransactionEventHeader.Builder setId(java.lang.String value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * GUID (UUID) of the event making it globally identifiable
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * GUID (UUID) of the event making it globally identifiable
      * @return This builder.
      */
    public mvp.kafka.avro.model.TransactionEventHeader.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'sourceSystem' field.
      * Source system which created the event
      * @return The value.
      */
    public java.lang.String getSourceSystem() {
      return sourceSystem;
    }


    /**
      * Sets the value of the 'sourceSystem' field.
      * Source system which created the event
      * @param value The value of 'sourceSystem'.
      * @return This builder.
      */
    public mvp.kafka.avro.model.TransactionEventHeader.Builder setSourceSystem(java.lang.String value) {
      validate(fields()[1], value);
      this.sourceSystem = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'sourceSystem' field has been set.
      * Source system which created the event
      * @return True if the 'sourceSystem' field has been set, false otherwise.
      */
    public boolean hasSourceSystem() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'sourceSystem' field.
      * Source system which created the event
      * @return This builder.
      */
    public mvp.kafka.avro.model.TransactionEventHeader.Builder clearSourceSystem() {
      sourceSystem = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * Event timestamp creation in epoch format
      * @return The value.
      */
    public long getCreatedAt() {
      return createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * Event timestamp creation in epoch format
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public mvp.kafka.avro.model.TransactionEventHeader.Builder setCreatedAt(long value) {
      validate(fields()[2], value);
      this.createdAt = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * Event timestamp creation in epoch format
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * Event timestamp creation in epoch format
      * @return This builder.
      */
    public mvp.kafka.avro.model.TransactionEventHeader.Builder clearCreatedAt() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TransactionEventHeader build() {
      try {
        TransactionEventHeader record = new TransactionEventHeader();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
        record.sourceSystem = fieldSetFlags()[1] ? this.sourceSystem : (java.lang.String) defaultValue(fields()[1]);
        record.createdAt = fieldSetFlags()[2] ? this.createdAt : (java.lang.Long) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TransactionEventHeader>
    WRITER$ = (org.apache.avro.io.DatumWriter<TransactionEventHeader>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TransactionEventHeader>
    READER$ = (org.apache.avro.io.DatumReader<TransactionEventHeader>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.id);

    out.writeString(this.sourceSystem);

    out.writeLong(this.createdAt);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.id = in.readString();

      this.sourceSystem = in.readString();

      this.createdAt = in.readLong();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.id = in.readString();
          break;

        case 1:
          this.sourceSystem = in.readString();
          break;

        case 2:
          this.createdAt = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










