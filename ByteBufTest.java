package com.editbox.database.serialize;

import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ByteBufTest {

    @Test
    void positionAfterPutIntTest() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(1234567890);
        assertEquals(4, buf.getPosition());
    }

    @Test
    void positionAfterSetPositionTest() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(1234567890);
        buf.setPosition(0);
        assertEquals(0, buf.getPosition());
    }

    @Test
    void positionAfterGetIntTest() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(1234567890);
        buf.setPosition(0);
        buf.getInt();
        assertEquals(4, buf.getPosition());
    }

    @Test
    void limitAfterPutTest() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(1234567890);
        assertEquals(4, buf.getLimit());
    }

    @Test
    void byteTest() {
        byte value = (byte) 0x55;
        ByteBuf buf = new ByteBuf();
        buf.putByte(value);
        buf.setPosition(0);
        assertEquals(value, buf.getByte());
    }

    @Test
    void booleanTest() {
        ByteBuf buf = new ByteBuf();
        buf.putBoolean(true);
        buf.putBoolean(false);
        buf.putBoolean(true);
        buf.setPosition(0);
        assertTrue(buf.getBoolean());
        assertFalse(buf.getBoolean());
        assertTrue(buf.getBoolean());
    }

    @Test
    void shortTest() {
        ByteBuf buf = new ByteBuf();
        buf.putShort((short) 22222);
        buf.putShort((short) -22222);
        buf.setPosition(0);
        assertEquals((short) 22222, buf.getShort());
        assertEquals((short) -22222, buf.getShort());
    }

    @Test
    void intTest() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(555555555);
        buf.putInt(-555555555);
        buf.setPosition(0);
        assertEquals(555555555, buf.getInt());
        assertEquals(-555555555, buf.getInt());
    }

    @Test
    void partInt0Test() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(0x12345364, 0);
        assertEquals(0, buf.getPosition());
    }

    @Test
    void partInt1Test() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(0x12345364, 1);
        assertEquals(1, buf.getPosition());
        buf.setPosition(0);
        assertEquals(0x64, buf.getInt(1));
    }

    @Test
    void partInt2Test() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(0x12345364, 2);
        assertEquals(2, buf.getPosition());
        buf.setPosition(0);
        assertEquals(0x5364, buf.getInt(2));
    }

    @Test
    void partInt3Test() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(0x12345364, 3);
        assertEquals(3, buf.getPosition());
        buf.setPosition(0);
        assertEquals(0x345364, buf.getInt(3));
    }

    @Test
    void partInt4Test() {
        ByteBuf buf = new ByteBuf();
        buf.putInt(0x12345364, 4);
        assertEquals(4, buf.getPosition());
        buf.setPosition(0);
        assertEquals(0x12345364, buf.getInt(4));
    }

    @Test
    void longTest() {
        ByteBuf buf = new ByteBuf();
        buf.putLong(5555555555555555555L);
        buf.putLong(-5555555555555555555L);
        buf.setPosition(0);
        assertEquals(5555555555555555555L, buf.getLong());
        assertEquals(-5555555555555555555L, buf.getLong());
    }

    @Test
    void partLong0Test() {
        ByteBuf buf = new ByteBuf();
        buf.putLong(0x12345678_90ABCDEFL, 0);
        assertEquals(0, buf.getPosition());
    }

    @Test
    void partLong4Test() {
        ByteBuf buf = new ByteBuf();
        buf.putLong(0x12345678_90ABCDEFL, 4);
        assertEquals(4, buf.getPosition());
        buf.setPosition(0);
        assertEquals(0x90ABCDEFL, buf.getLong(4));
    }

    @Test
    void partLong8Test() {
        ByteBuf buf = new ByteBuf();
        buf.putLong(0x12345678_90ABCDEFL, 8);
        assertEquals(8, buf.getPosition());
        buf.setPosition(0);
        assertEquals(0x12345678_90ABCDEFL, buf.getLong(8));
    }

    @Test
    void floatTest() {
        ByteBuf buf = new ByteBuf();
        buf.putFloat(3.1415929f);
        assertEquals(4, buf.getPosition());
        buf.setPosition(0);
        assertEquals(3.1415929f, buf.getFloat());
    }

    @Test
    void doubleTest() {
        ByteBuf buf = new ByteBuf();
        buf.putDouble(3.141592653589793);
        assertEquals(8, buf.getPosition());
        buf.setPosition(0);
        assertEquals(3.141592653589793, buf.getDouble());
    }

    @Test
    void uuidTest() {
        ByteBuf buf = new ByteBuf();
        UUID uuid = UUID.randomUUID();
        buf.putUuid(uuid);
        assertEquals(16, buf.getPosition());
        buf.setPosition(0);
        assertEquals(uuid, buf.getUuid());
    }

    @Test
    void arrayTest() {
        ByteBuf buf = new ByteBuf();
        byte[] array = new byte[]{12, 34, 56, 78, 90};
        buf.putArray(array);
        assertEquals(5, buf.getPosition());
        buf.setPosition(0);
        assertArrayEquals(array, buf.getArray(5));
    }

    @Test
    void stringTest() {
        ByteBuf buf = new ByteBuf();
        String string = "Hello world!";
        buf.putString(string);
        buf.setPosition(0);
        assertEquals(string, buf.getString(12));
    }

    @Test
    void truncatedStringTest() {
        ByteBuf buf = new ByteBuf();
        String string = "Hello world!";
        buf.putString(string, 5);
        buf.setPosition(0);
        assertEquals("Hello", buf.getString(5));
    }

    @Test
    void resizeByPutTest() {
        ByteBuf buf = new ByteBuf(4);
        buf.putInt(1);
        buf.putInt(2);
        buf.putInt(3);
        assertEquals(12, buf.getPosition());
    }

    @Test
    void resizeBySetPositionTest() {
        ByteBuf buf = new ByteBuf(4);
        buf.setPosition(100);
        assertEquals(100, buf.getPosition());
    }
}
