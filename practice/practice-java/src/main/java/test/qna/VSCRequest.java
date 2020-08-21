package test.qna;

public class VSCRequest {

  public void request() {
  }
}

class STTRequest implements Request{
  @Override
  public void push(VSCRequest vscRequest) {
    speech(vscRequest);
  }

  public void speech(VSCRequest vscRequest) {
    vscRequest.request();
    // 샬라
  }
}

class ChatRequest implements Request{

  @Override
  public void push(VSCRequest vscRequest) {
    send(vscRequest);
    //샬라
  }

  public void send(VSCRequest vscRequest) {
    vscRequest.request();   
  }
}

interface Request {
  void push(VSCRequest vscRequest);
}


interface VSCRequest2 {
  void request();  // 앞단에 제공될 놈
}

class STTRequest2 implements VSCRequest2{
  @Override
  public void request() {
    // 샬라
    send();
  }

  public void send() {
    // 기존코드
  }
}

class ChatRequest2 implements VSCRequest2{

  @Override
  public void request() {
    // 샬라
    speech();
  }

  public void speech() {
    // 기존코드
  }
}




