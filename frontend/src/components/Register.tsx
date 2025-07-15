import {
  Modal,
  ModalContent,
  ModalBody,
  Button,
  useDisclosure,
  Form,
  Input,
} from "@heroui/react";
import axios from "axios";

export default function Register() {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();

  return (
    <>
      <Button onPress={onOpen} className="text-4xl">+</Button>
      <Modal isOpen={isOpen} onOpenChange={onOpenChange} size="3xl" radius="sm">
        <ModalContent className="!h-[40vh]">
          {(onClose) => (
            <ModalBody className="flex flex-row gap-10">
              <div className="flex flex-col items-center justify-center w-1/2 p-4 border-r-2 border-slate-950">
                <div className="text-4xl mb-4">Register</div>
                <ul className="list-disc">
                  <li className="text-center mb-2">
                    by registering a certain user you will be giving them the
                    editor previlages
                  </li>
                  <li className="text-center">
                    Editors will be able to manipulate both tasks and posts
                  </li>
                </ul>
              </div>
              <div className="flex flex-col items-center justify-center w-1/2 my-6">
                <Form
                  className="w-full max-w-xs flex flex-col gap-4"
                  onSubmit={(e) => {
                    e.preventDefault();
                    let data = Object.fromEntries(
                      new FormData(e.currentTarget)
                    );
                    console.log(data);
                    axios
                      .post("/add-user", data)
                      .then((response) => {
                        console.log("User added:", response.data);
                      })
                      .catch((error) => {
                        console.error("Error adding user:", error);
                      });
                  }}
                >
                  <Input
                    isRequired
                    errorMessage="Please enter a valid username"
                    label="Username"
                    labelPlacement="outside"
                    name="username"
                    placeholder="Enter your username"
                    type="text"
                  />
                  <Input
                    isRequired
                    errorMessage="Please enter a valid email"
                    label="Email"
                    labelPlacement="outside"
                    name="email"
                    placeholder="Enter your email"
                    type="email"
                  />
                  <Input
                    isRequired
                    errorMessage="Please enter a valid password"
                    label="Password"
                    labelPlacement="outside"
                    name="password"
                    placeholder="Enter your password"
                    type="password"
                  />
                  <div className="flex gap-2">
                    <Button
                      className="bg-slate-950 rounded-md text-slate-200"
                      type="submit"
                    >
                      Submit
                    </Button>
                    <Button
                      className="bg-slate-200 rounded-md text-slate-950"
                      type="reset"
                    >
                      Reset
                    </Button>
                  </div>
                </Form>
              </div>
            </ModalBody>
          )}
        </ModalContent>
      </Modal>
    </>
  );
}
