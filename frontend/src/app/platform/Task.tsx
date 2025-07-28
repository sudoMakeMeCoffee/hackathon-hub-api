"use client";

import { useState, useEffect } from "react";
import axios from "axios";
import * as z from "zod";
import { useForm, SubmitHandler } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

const user = z.object({
  id: z.string().optional(),
});

const subTaskSchema = z.object({
  id: z.string().optional(),
  title: z.string().min(2).max(24),
  description: z.string().min(8).max(48).optional(),
  assinee: z.array(user),
});

const taskSchema = z.object({
  id: z.string().optional(),
  title: z.string().min(2).max(24),
  description: z.string().min(8).max(48).optional(),
  subtask: z.array(subTaskSchema),
  assinee: z.array(user),
});

type Task = z.infer<typeof taskSchema>;

const Task = () => {
  return <div>Task</div>;
};

export default Task;
